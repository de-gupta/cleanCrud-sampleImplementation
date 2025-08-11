package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.facade;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.AbstractFetchServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskFetchServiceFacade extends
		AbstractFetchServiceFacade<TaskAPIModelResponse, Long, TaskDomainModel, TaskDomainModelResponse, Long>
		implements FetchServiceFacade<TaskAPIModelResponse, Long>
{
	TaskFetchServiceFacade(final FetchService<TaskDomainModel, Long> service,
						   final DomainToAPIResponseAdapter<TaskAPIModelResponse, Long, TaskDomainModelResponse> responseMapper,
						   final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> domainResponseBuilder,
						   final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, responseMapper, domainResponseBuilder, idAdapter);
	}
}