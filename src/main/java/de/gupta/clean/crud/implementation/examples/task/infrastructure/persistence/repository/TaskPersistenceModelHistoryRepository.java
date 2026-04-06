package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.JpaTriTemporalHistoryRepositoryAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TaskPersistenceModelHistoryRepository
		extends JpaTriTemporalHistoryRepositoryAdapter<UUID, TaskPersistenceModelHistory>
		implements TriTemporalHistoryRepository<UUID, TaskPersistenceModelHistory>
{
	TaskPersistenceModelHistoryRepository(final TaskPersistenceModelHistoryJpaRepository repository)
	{
		super(repository);
	}
}