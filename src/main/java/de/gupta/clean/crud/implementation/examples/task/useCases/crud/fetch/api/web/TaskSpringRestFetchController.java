package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.api.web;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.security.TaskEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.AbstractSpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.SpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Fetch", description = "Fetch Operations pertaining to Task")
@RestController
@RequestMapping("/task/fetch")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskEndpointSecurityPolicy.class)
class TaskSpringRestFetchController extends
		AbstractSpringRestFetchController<TaskAPIModelResponse, Long>
		implements SpringRestFetchController<TaskAPIModelResponse, Long>
{
	TaskSpringRestFetchController(final FetchServiceFacade<TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}