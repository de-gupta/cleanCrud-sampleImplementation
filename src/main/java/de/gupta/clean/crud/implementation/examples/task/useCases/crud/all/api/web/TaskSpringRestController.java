package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.api.web;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.all.api.web.AbstractSpringRestCrudController;
import de.gupta.clean.crud.template.useCases.crud.all.api.web.SpringRestCrudController;
import de.gupta.clean.crud.template.useCases.crud.all.facade.CrudServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Crud", description = "Crud Operations pertaining to Task")
@RestController
@RequestMapping("/task")
@Deprecated
class TaskSpringRestController extends
		AbstractSpringRestCrudController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
		implements SpringRestCrudController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskSpringRestController(
			final CrudServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}