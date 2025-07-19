package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.AbstractFetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import org.springframework.stereotype.Service;

@Service
final class TaskFetchService extends
		AbstractFetchService<TaskDomainModelResponse, Long, TaskDomainModel>
		implements FetchService<TaskDomainModelResponse, Long>
{
	TaskFetchService(final FetchPersistenceService<Long, TaskDomainModel> persistenceService,
					 final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> modelMapper,
					 final DomainSecurityPolicy<TaskDomainModel> domainSecurityPolicy)
	{
		super(persistenceService, modelMapper, domainSecurityPolicy);
	}
}