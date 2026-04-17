package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.update.api.application;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.AbstractUpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionUpdateApplicationController extends
		AbstractUpdateApplicationController<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long>
		implements
		UpdateApplicationController<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long>
{
	TaskVersionUpdateApplicationController(
			final UpdateServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}