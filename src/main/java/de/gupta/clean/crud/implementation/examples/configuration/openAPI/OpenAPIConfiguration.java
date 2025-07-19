package de.gupta.clean.crud.implementation.examples.configuration.openAPI;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotatedMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Function;

@Configuration
@Profile("swagger")
class OpenAPIConfiguration
{
	private static final Map<Class<? extends Annotation>, Function<Annotation, Collection<String>>>
			HTTP_METHOD_EXTRACTORS = Map.of(
			GetMapping.class, _ -> List.of("GET"),
			PostMapping.class, _ -> List.of("POST"),
			PutMapping.class, _ -> List.of("PUT"),
			PatchMapping.class, _ -> List.of("PATCH"),
			DeleteMapping.class, _ -> List.of("DELETE"),
			RequestMapping.class, annotation ->
			{
				final RequestMethod[] methods = ((RequestMapping) annotation).method();
				return methods.length == 0
						? List.of("GET")
						: Arrays.stream(methods).map(Enum::name).toList();
			}
	);

	@Bean
	public OpenAPI customOpenAPI()
	{
		return new OpenAPI().components(new Components().addSecuritySchemes("bearer-key",
									new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
							.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
	}

	@Bean
	public OperationCustomizer customizeOperationId()
	{
		return (operation, handlerMethod) ->
		{
			final String controllerName = handlerMethod.getBeanType().getSimpleName()
													   .replace("Controller", "")
													   .replace("Impl", "")
													   .replace("Rest", "")
													   .replace("Spring", "");
			final String methodName = handlerMethod.getMethod().getName();
			final String methodType = getHttpMethodType(handlerMethod);

			operation.setOperationId(controllerName + "." + methodType + "." + methodName);
			return operation;
		};
	}

	private String getHttpMethodType(final HandlerMethod handlerMethod)
	{
		return HTTP_METHOD_EXTRACTORS.entrySet().stream()
									 .map(entry -> getHttpMethodTypes(handlerMethod, entry.getKey(), entry.getValue()))
									 .flatMap(Collection::stream)
									 .distinct()
									 .reduce((a, b) -> a + "_" + b)
									 .orElse("UNKNOWN");
	}

	private Collection<String> getHttpMethodTypes(final HandlerMethod handlerMethod,
												  final Class<? extends Annotation> annotationClass,
												  final Function<Annotation, Collection<String>> extractor)
	{
		return Optional.ofNullable(handlerMethod)
					   .map(AnnotatedMethod::getMethod)
					   .filter(m -> AnnotatedElementUtils.hasAnnotation(m, annotationClass))
					   .map(m -> AnnotatedElementUtils.findMergedAnnotation(m, annotationClass))
					   .map(extractor)
					   .orElse(Collections.emptyList());
	}
}