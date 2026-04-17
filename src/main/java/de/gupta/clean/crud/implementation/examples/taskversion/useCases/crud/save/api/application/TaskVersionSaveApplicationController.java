package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.save.api.application;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.AbstractSaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.SaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionSaveApplicationController extends
		AbstractSaveApplicationController<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse>
		implements SaveApplicationController<TaskVersionAPIModelCreate,
		TaskVersionAPIModelResponse>
{
	TaskVersionSaveApplicationController(
			final SaveServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse> service)
	{
		super(service);
	}
}