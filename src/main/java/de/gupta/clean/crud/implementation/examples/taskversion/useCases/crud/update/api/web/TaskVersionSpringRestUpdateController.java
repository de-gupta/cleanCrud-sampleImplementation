package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.update.api.web;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.security.TaskVersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.AbstractSpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.SpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TaskVersion Update", description = "Update Operations pertaining to TaskVersion")
@RestController
@RequestMapping("/taskversion/update")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskVersionEndpointSecurityPolicy.class)
class TaskVersionSpringRestUpdateController extends
		AbstractSpringRestUpdateController<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long>
		implements
		SpringRestUpdateController<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long>
{
	TaskVersionSpringRestUpdateController(
			final UpdateServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}