package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.facade;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.save.facade.AbstractSaveServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskSaveServiceFacade extends
		AbstractSaveServiceFacade<TaskAPIModelCreate, TaskAPIModelResponse,
				TaskDomainModelCreate, TaskDomainModelResponse, Long>
		implements SaveServiceFacade<TaskAPIModelCreate, TaskAPIModelResponse>
{
	TaskSaveServiceFacade(
			final SaveService<TaskDomainModelCreate, TaskDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TaskAPIModelCreate, TaskDomainModelCreate> createMapper,
			final DomainToAPIResponseAdapter<TaskAPIModelResponse, Long, TaskDomainModelResponse> responseMapper)
	{
		super(service, createMapper, responseMapper);
	}
}