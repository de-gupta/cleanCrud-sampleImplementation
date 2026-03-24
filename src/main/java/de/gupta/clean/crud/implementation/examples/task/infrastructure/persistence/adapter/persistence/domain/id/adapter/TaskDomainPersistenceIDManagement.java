package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model.TaskDomainPersistenceAdapterHistoryJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model.TaskDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model.TaskDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.domain.model.builder.BuilderFactories;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, TaskDomainPersistenceAdapterModel,
		TaskDomainPersistenceAdapterHistoryModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	TaskDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, TaskDomainPersistenceAdapterModel> repository,
			final TaskDomainPersistenceAdapterHistoryJpaRepository historyRepository,
			@Qualifier("taskLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator)
	{
		super(repository,
				BuilderFactories.of(TaskDomainPersistenceAdapterModel::builder),
				historyRepository,
				BuilderFactories.of(TaskDomainPersistenceAdapterHistoryModel::builder),
				domainIDGenerator);
	}
}