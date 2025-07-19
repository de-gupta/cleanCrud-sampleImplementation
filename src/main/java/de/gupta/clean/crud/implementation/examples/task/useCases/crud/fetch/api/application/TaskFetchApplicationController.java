package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.api.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.AbstractFetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.FetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskFetchApplicationController extends AbstractFetchApplicationController<TaskAPIModelResponse, Long>
		implements FetchApplicationController<TaskAPIModelResponse, Long>
{
	TaskFetchApplicationController(final FetchServiceFacade<TaskAPIModelResponse, Long> service)
	{
		super(service);
	}
}