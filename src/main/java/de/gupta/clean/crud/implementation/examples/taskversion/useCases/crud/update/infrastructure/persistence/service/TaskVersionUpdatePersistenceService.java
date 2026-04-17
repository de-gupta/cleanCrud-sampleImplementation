package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.update.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.AbstractUpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TaskVersionUpdatePersistenceService
		extends AbstractUpdatePersistenceService<Long, TaskVersionDomainModel, UUID, TaskVersionPersistenceModel>
		implements UpdatePersistenceService<Long, TaskVersionDomainModel>
{
	TaskVersionUpdatePersistenceService(
			final FetchPersistenceModelRepository<TaskVersionPersistenceModel, UUID> fetchRepository,
			final SavePersistenceModelRepository<TaskVersionPersistenceModel> saveRepository,
			final UpdatePersistenceModelRepository<TaskVersionPersistenceModel> updateRepository,
			final DomainPersistenceModelAdapter<TaskVersionDomainModel, TaskVersionPersistenceModel> modelAdapter,
			@Qualifier("taskVersionDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("taskVersionDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, saveRepository, updateRepository, modelAdapter, idAdapter, idManagement);
	}
}
