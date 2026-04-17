package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.delete.api.web;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.security.TaskVersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.AbstractSpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.SpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TaskVersion Delete", description = "Delete Operations pertaining to TaskVersion")
@RestController
@RequestMapping("/taskversion/delete")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskVersionEndpointSecurityPolicy.class)
class TaskVersionSpringRestDeleteController extends AbstractSpringRestDeleteController<Long>
		implements SpringRestDeleteController<Long>
{
	TaskVersionSpringRestDeleteController(
			@Qualifier("taskVersionDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}