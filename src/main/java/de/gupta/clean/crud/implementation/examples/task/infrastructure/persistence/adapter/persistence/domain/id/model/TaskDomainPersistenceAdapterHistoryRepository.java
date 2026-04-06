package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterHistoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TaskDomainPersistenceAdapterHistoryRepository
		extends
		AbstractDomainPersistenceAdapterHistoryJpaRepository<Long, UUID, TaskDomainPersistenceAdapterHistoryModel>
{
	TaskDomainPersistenceAdapterHistoryRepository(final TaskDomainPersistenceAdapterHistoryJpaRepository repository)
	{
		super(repository);
	}
}