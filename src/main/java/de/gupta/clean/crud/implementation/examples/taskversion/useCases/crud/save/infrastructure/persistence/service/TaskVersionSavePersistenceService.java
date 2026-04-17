package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.save.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.AbstractSavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TaskVersionSavePersistenceService extends
		AbstractSavePersistenceService<Long, TaskVersionDomainModel, UUID, TaskVersionPersistenceModel>
		implements SavePersistenceService<Long, TaskVersionDomainModel>
{
	TaskVersionSavePersistenceService(
			final SavePersistenceModelRepository<TaskVersionPersistenceModel> repository,
			final DomainPersistenceModelAdapter<TaskVersionDomainModel, TaskVersionPersistenceModel> modelAdapter,
			@Qualifier("taskVersionDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(repository, modelAdapter, idManagement);
	}
}
