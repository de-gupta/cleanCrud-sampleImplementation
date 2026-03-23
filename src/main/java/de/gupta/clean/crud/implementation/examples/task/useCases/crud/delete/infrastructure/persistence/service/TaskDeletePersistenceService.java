package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.AbstractDeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskDeletePersistenceService")
final class TaskDeletePersistenceService extends AbstractDeletePersistenceService<Long, UUID, TaskPersistenceModel>
		implements DeletePersistenceService<Long>
{
	TaskDeletePersistenceService(
			final FetchPersistenceModelRepository<TaskPersistenceModel, UUID> fetchRepository,
			@Qualifier("taskDeletePersistenceModelRepository") final DeletePersistenceModelRepository<UUID> deleteRepository,
			@Qualifier("taskDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(fetchRepository, deleteRepository, idAdapter);
	}
}