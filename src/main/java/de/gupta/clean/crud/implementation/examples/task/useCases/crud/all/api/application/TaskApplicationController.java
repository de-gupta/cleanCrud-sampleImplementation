package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.api.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.all.api.application.AbstractApplicationCrudController;
import de.gupta.clean.crud.template.useCases.crud.all.api.application.ApplicationCrudController;
import de.gupta.clean.crud.template.useCases.crud.all.facade.CrudServiceFacade;
import org.springframework.stereotype.Component;

@Component
@Deprecated
final class TaskApplicationController extends
		AbstractApplicationCrudController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
		implements ApplicationCrudController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskApplicationController(
			final CrudServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}