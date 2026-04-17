package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.save.api.web;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.security.TaskVersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.AbstractSpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.SpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TaskVersion Save", description = "Save Operations pertaining to TaskVersion")
@RestController
@RequestMapping("/taskversion/save")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TaskVersionEndpointSecurityPolicy.class)
class TaskVersionSpringRestSaveController extends
		AbstractSpringRestSaveController<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse>
		implements SpringRestSaveController<TaskVersionAPIModelCreate,
		TaskVersionAPIModelResponse>
{
	TaskVersionSpringRestSaveController(
			final SaveServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse> service)
	{
		super(service);
	}
}