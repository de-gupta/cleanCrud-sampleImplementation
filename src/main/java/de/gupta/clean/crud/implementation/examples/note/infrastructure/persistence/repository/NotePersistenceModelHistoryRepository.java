package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.JpaTriTemporalHistoryRepositoryAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class NotePersistenceModelHistoryRepository
		extends JpaTriTemporalHistoryRepositoryAdapter<UUID, NotePersistenceModelHistory>
		implements TriTemporalHistoryRepository<UUID, NotePersistenceModelHistory>
{
	public NotePersistenceModelHistoryRepository(
			final NotePersistenceModelHistoryJpaRepository repository)
	{
		super(repository);
	}
}
