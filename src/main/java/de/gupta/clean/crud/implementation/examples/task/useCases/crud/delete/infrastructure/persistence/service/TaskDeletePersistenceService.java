package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.infrastructure.persistence.service;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.AbstractDeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskDeletePersistenceService")
final class TaskDeletePersistenceService extends AbstractDeletePersistenceService<Long, UUID>
		implements DeletePersistenceService<Long>
{
	TaskDeletePersistenceService(
			@Qualifier("taskDeletePersistenceModelRepository") final DeletePersistenceModelRepository<UUID> repository,
			@Qualifier("taskDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, idAdapter);
	}
}