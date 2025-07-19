package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.AbstractSavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TaskSavePersistenceService extends
		AbstractSavePersistenceService<Long, TaskDomainModel,
				UUID, TaskPersistenceModel>
		implements SavePersistenceService<Long, TaskDomainModel>
{
	TaskSavePersistenceService(
			final SavePersistenceModelRepository<TaskPersistenceModel> repository,
			final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter,
			@Qualifier("taskDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement
	)
	{
		super(repository, modelAdapter, idManagement);
	}
}