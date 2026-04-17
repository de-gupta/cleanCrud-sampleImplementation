package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.fetch.facade;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.AbstractFetchServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionFetchServiceFacade extends
		AbstractFetchServiceFacade<TaskVersionAPIModelResponse, Long, TaskVersionDomainModel, TaskVersionDomainModelResponse, Long>
		implements FetchServiceFacade<TaskVersionAPIModelResponse,
		Long>
{
	TaskVersionFetchServiceFacade(final FetchService<TaskVersionDomainModel, Long> service,
	                              final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse> responseMapper,
	                              final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse> responseBuilder,
	                              final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, responseMapper, responseBuilder, idAdapter);
	}
}