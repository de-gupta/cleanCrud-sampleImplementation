package de.gupta.clean.crud.implementation.examples.creation.quarantine;

import de.gupta.clean.crud.implementation.examples.setup.IntegrationTest;
import de.gupta.clean.crud.template.useCases.operation.creation.quarantine.api.web.DefaultSpringRestCreationQuarantineController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class CreationQuarantineApiWiringITCase
{
	@Autowired(required = false)
	private DefaultSpringRestCreationQuarantineController controller;

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Test
	void shouldRegisterCreationQuarantineRestController()
	{
		assertThat(controller).isNotNull();

		var patterns = handlerMapping.getHandlerMethods().entrySet().stream()
		                             .filter(entry -> entry.getValue().getBeanType()
		                                                   .equals(DefaultSpringRestCreationQuarantineController.class))
		                             .map(Map.Entry::getKey)
		                             .map(RequestMappingInfo::toString)
		                             .collect(Collectors.toSet());

		assertThat(patterns).anyMatch(pattern -> pattern.contains("/internal/creation-quarantines/{id}"));
	}
}
