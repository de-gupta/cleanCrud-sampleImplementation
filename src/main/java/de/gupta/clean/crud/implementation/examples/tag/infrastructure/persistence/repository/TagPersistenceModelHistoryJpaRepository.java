package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagPersistenceModelHistoryJpaRepository
		extends TriTemporalHistoryJpaRepository<UUID, TagPersistenceModelHistory>
{
}