package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.fetch.api.application;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.AbstractFetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.FetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionFetchApplicationController extends
		AbstractFetchApplicationController<TaskVersionAPIModelResponse, Long>
		implements FetchApplicationController<TaskVersionAPIModelResponse, Long>
{
	TaskVersionFetchApplicationController(final FetchServiceFacade<TaskVersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}