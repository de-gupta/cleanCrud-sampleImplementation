package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.transaction.PersistenceTransactionRunner;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.AbstractUpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TaskUpdatePersistenceService
		extends AbstractUpdatePersistenceService<Long, TaskDomainModel, UUID, TaskPersistenceModel>
		implements UpdatePersistenceService<Long, TaskDomainModel>
{
	TaskUpdatePersistenceService(
			final FetchPersistenceModelRepository<TaskPersistenceModel, UUID> fetchRepository,
			final SavePersistenceModelRepository<TaskPersistenceModel> saveRepository,
			final UpdatePersistenceModelRepository<TaskPersistenceModel> updateRepository,
			final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter,
			@Qualifier("taskDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("taskDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement,
			final PersistenceTransactionRunner transactionRunner)
	{
		super(fetchRepository, saveRepository, updateRepository, modelAdapter, idAdapter, idManagement,
				transactionRunner);
	}
}