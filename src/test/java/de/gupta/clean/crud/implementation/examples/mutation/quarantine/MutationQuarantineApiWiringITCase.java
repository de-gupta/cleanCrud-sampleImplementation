package de.gupta.clean.crud.implementation.examples.mutation.quarantine;

import de.gupta.clean.crud.implementation.examples.setup.IntegrationTest;
import de.gupta.clean.crud.template.useCases.mutation.quarantine.api.web.DefaultSpringRestMutationQuarantineController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class MutationQuarantineApiWiringITCase
{
	@Autowired(required = false)
	private DefaultSpringRestMutationQuarantineController controller;

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@Test
	void shouldRegisterMutationQuarantineRestController()
	{
		assertThat(controller).isNotNull();

		var patterns = handlerMapping.getHandlerMethods().entrySet().stream()
		                             .filter(entry -> entry.getValue().getBeanType()
		                                                   .equals(DefaultSpringRestMutationQuarantineController.class))
		                             .map(Map.Entry::getKey)
		                             .map(RequestMappingInfo::toString)
		                             .collect(Collectors.toSet());

		assertThat(patterns).anyMatch(pattern -> pattern.contains("/internal/mutation-quarantines/{id}"));
	}
}