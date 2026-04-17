package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.delete.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.AbstractDeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskVersionDeletePersistenceService")
final class TaskVersionDeletePersistenceService
		extends AbstractDeletePersistenceService<Long, UUID, TaskVersionPersistenceModel>
		implements DeletePersistenceService<Long>
{
	TaskVersionDeletePersistenceService(
			final FetchPersistenceModelRepository<TaskVersionPersistenceModel, UUID> fetchRepository,
			@Qualifier("taskVersionDeletePersistenceModelRepository") final DeletePersistenceModelRepository<UUID> deleteRepository,
			@Qualifier("taskVersionDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("taskVersionDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, deleteRepository, idAdapter, idManagement);
	}
}