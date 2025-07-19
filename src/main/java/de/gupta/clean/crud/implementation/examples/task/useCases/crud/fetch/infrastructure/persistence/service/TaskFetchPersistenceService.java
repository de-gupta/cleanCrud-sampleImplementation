package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.AbstractFetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskFetchPersistenceService
		extends AbstractFetchPersistenceService<Long, TaskDomainModel, UUID, TaskPersistenceModel>
		implements FetchPersistenceService<Long, TaskDomainModel>
{
	TaskFetchPersistenceService(final FetchPersistenceModelRepository<TaskPersistenceModel, UUID> repository,
								final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter,
								@Qualifier("taskDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, modelAdapter, idAdapter);
	}
}