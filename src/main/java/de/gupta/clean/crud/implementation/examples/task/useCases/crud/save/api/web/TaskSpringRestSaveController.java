package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.api.web;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.AbstractSpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.SpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Save", description = "Save Operations pertaining to Task")
@RestController
@RequestMapping("/task/save")
class TaskSpringRestSaveController extends
		AbstractSpringRestSaveController<TaskAPIModelCreate, TaskAPIModelResponse>
		implements SpringRestSaveController<TaskAPIModelCreate, TaskAPIModelResponse>
{
	TaskSpringRestSaveController(
			final SaveServiceFacade<TaskAPIModelCreate, TaskAPIModelResponse> service)
	{
		super(service);
	}
}