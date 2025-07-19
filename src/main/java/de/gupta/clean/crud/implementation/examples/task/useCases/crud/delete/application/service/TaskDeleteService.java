package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.AbstractDeleteService;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeleteService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("taskDeleteService")
final class TaskDeleteService extends AbstractDeleteService<Long, TaskDomainModel> implements DeleteService<Long>
{
	TaskDeleteService(
			final FetchPersistenceService<Long, TaskDomainModel> fetchService,
			@Qualifier("taskDeletePersistenceService") final DeletePersistenceService<Long> persistenceService,
			final DeletionPolicy<TaskDomainModel> deletionPolicy,
			final DomainSecurityPolicy<TaskDomainModel> domainSecurityPolicy)
	{
		super(fetchService, persistenceService, deletionPolicy, domainSecurityPolicy);
	}
}