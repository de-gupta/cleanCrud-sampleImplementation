package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.AbstractFetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import org.springframework.stereotype.Service;

@Service
final class TaskFetchService extends AbstractFetchService<Long, TaskDomainModel>
		implements FetchService<TaskDomainModel, Long>
{
	TaskFetchService(final FetchPersistenceService<Long, TaskDomainModel> persistenceService,
					 final DomainSecurityPolicy<TaskDomainModel> domainSecurityPolicy)
	{
		super(persistenceService, domainSecurityPolicy);
	}
}