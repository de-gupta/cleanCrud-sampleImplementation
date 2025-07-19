package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.api.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.AbstractUpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskUpdateApplicationController extends
		AbstractUpdateApplicationController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
		implements UpdateApplicationController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskUpdateApplicationController(
			final UpdateServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}