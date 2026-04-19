package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterHistoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TagDomainPersistenceAdapterHistoryRepository
		extends
		AbstractDomainPersistenceAdapterHistoryJpaRepository<Long, UUID, TagDomainPersistenceAdapterHistoryModel>
{
	public TagDomainPersistenceAdapterHistoryRepository(
			final TagDomainPersistenceAdapterHistoryJpaRepository repository)
	{
		super(repository);
	}
}