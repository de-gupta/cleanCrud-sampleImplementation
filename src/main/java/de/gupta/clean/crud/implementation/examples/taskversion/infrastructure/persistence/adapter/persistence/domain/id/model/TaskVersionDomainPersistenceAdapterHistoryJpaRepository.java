package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskVersionDomainPersistenceAdapterHistoryJpaRepository
		extends TriTemporalHistoryJpaRepository<Long, TaskVersionDomainPersistenceAdapterHistoryModel>
{
}