package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.JpaTriTemporalHistoryRepositoryAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TagPersistenceModelHistoryRepository
		extends JpaTriTemporalHistoryRepositoryAdapter<UUID, TagPersistenceModelHistory>
		implements TriTemporalHistoryRepository<UUID, TagPersistenceModelHistory>
{
	public TagPersistenceModelHistoryRepository(
			final TagPersistenceModelHistoryJpaRepository repository)
	{
		super(repository);
	}
}
