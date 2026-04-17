package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.fetch.api.web;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.security.TaskVersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.AbstractSpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.SpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TaskVersion Fetch", description = "Fetch Operations pertaining to TaskVersion")
@RestController
@RequestMapping("/taskversion/fetch")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskVersionEndpointSecurityPolicy.class)
class TaskVersionSpringRestFetchController extends
		AbstractSpringRestFetchController<TaskVersionAPIModelResponse, Long>
		implements SpringRestFetchController<TaskVersionAPIModelResponse, Long>
{
	TaskVersionSpringRestFetchController(final FetchServiceFacade<TaskVersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}