package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.api.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.AbstractSaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.SaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskSaveApplicationController extends
		AbstractSaveApplicationController<TaskAPIModelCreate, TaskAPIModelResponse>
		implements SaveApplicationController<TaskAPIModelCreate, TaskAPIModelResponse>
{
	TaskSaveApplicationController(
			final SaveServiceFacade<TaskAPIModelCreate, TaskAPIModelResponse> service)
	{
		super(service);
	}
}