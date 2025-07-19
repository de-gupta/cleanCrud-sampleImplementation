package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.all.application.service.CrudPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.service.AbstractCrudPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.service.PersistenceModelCrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskCrudPersistenceService
		extends AbstractCrudPersistenceService<Long, TaskDomainModel, UUID, TaskPersistenceModel>
		implements CrudPersistenceService<Long, TaskDomainModel>
{
	TaskCrudPersistenceService(final PersistenceModelCrudRepository<TaskPersistenceModel, UUID> repository,
							   final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter,
							   @Qualifier("taskDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
							   @Qualifier("taskDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(repository, modelAdapter, idAdapter, idManagement);
	}
}