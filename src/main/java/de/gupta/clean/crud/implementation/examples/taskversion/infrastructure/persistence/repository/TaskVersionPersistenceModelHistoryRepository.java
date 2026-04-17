package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.JpaTriTemporalHistoryRepositoryAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TaskVersionPersistenceModelHistoryRepository
		extends JpaTriTemporalHistoryRepositoryAdapter<UUID, TaskVersionPersistenceModelHistory>
		implements TriTemporalHistoryRepository<UUID, TaskVersionPersistenceModelHistory>
{
	public TaskVersionPersistenceModelHistoryRepository(
			final TaskVersionPersistenceModelHistoryJpaRepository repository)
	{
		super(repository);
	}
}
