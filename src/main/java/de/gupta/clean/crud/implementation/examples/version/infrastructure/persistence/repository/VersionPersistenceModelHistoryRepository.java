package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.JpaTriTemporalHistoryRepositoryAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class VersionPersistenceModelHistoryRepository
		extends JpaTriTemporalHistoryRepositoryAdapter<UUID, VersionPersistenceModelHistory>
		implements TriTemporalHistoryRepository<UUID, VersionPersistenceModelHistory>
{
	public VersionPersistenceModelHistoryRepository(
			final VersionPersistenceModelHistoryJpaRepository repository)
	{
		super(repository);
	}
}