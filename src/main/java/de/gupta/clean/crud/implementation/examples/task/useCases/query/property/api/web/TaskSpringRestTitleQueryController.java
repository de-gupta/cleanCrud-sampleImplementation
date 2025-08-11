package de.gupta.clean.crud.implementation.examples.task.useCases.query.property.api.web;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.security.TaskEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.query.property.api.web.AbstractSpringRestPropertyQueryController;
import de.gupta.clean.crud.template.useCases.query.property.api.web.SpringRestPropertyQueryController;
import de.gupta.clean.crud.template.useCases.query.property.facade.PropertyQueryServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Title Query", description = "Title Query Operations pertaining to Task")
@RestController
@RequestMapping("/task/query/title")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskEndpointSecurityPolicy.class)
class TaskSpringRestTitleQueryController
		extends AbstractSpringRestPropertyQueryController<String, TaskAPIModelResponse>
		implements SpringRestPropertyQueryController<String, TaskAPIModelResponse>
{
	TaskSpringRestTitleQueryController(final PropertyQueryServiceFacade<String, TaskAPIModelResponse> service)
	{
		super("title", service);
	}
}