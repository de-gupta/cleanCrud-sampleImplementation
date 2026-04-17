package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterHistoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TaskVersionDomainPersistenceAdapterHistoryRepository
		extends
		AbstractDomainPersistenceAdapterHistoryJpaRepository<Long, UUID, TaskVersionDomainPersistenceAdapterHistoryModel>
{
	public TaskVersionDomainPersistenceAdapterHistoryRepository(
			final TaskVersionDomainPersistenceAdapterHistoryJpaRepository repository)
	{
		super(repository);
	}
}