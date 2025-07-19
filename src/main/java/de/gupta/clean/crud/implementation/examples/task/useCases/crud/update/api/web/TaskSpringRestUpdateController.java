package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.api.web;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.AbstractSpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.SpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Update", description = "Update Operations pertaining to Task")
@RestController
@RequestMapping("/task/update")
class TaskSpringRestUpdateController extends
		AbstractSpringRestUpdateController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
		implements SpringRestUpdateController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskSpringRestUpdateController(
			final UpdateServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}