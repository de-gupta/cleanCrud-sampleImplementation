package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterHistoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class NoteDomainPersistenceAdapterHistoryRepository
		extends
		AbstractDomainPersistenceAdapterHistoryJpaRepository<Long, UUID, NoteDomainPersistenceAdapterHistoryModel>
{
	public NoteDomainPersistenceAdapterHistoryRepository(
			final NoteDomainPersistenceAdapterHistoryJpaRepository repository)
	{
		super(repository);
	}
}