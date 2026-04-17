package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskVersionPersistenceModelHistoryJpaRepository
		extends TriTemporalHistoryJpaRepository<UUID, TaskVersionPersistenceModelHistory>
{
}