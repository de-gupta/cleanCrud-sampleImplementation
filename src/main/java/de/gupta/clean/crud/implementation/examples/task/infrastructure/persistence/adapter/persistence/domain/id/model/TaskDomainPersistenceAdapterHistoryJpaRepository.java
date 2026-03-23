package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDomainPersistenceAdapterHistoryJpaRepository
		extends TriTemporalHistoryJpaRepository<Long, TaskDomainPersistenceAdapterHistoryModel>
{
}