package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterHistoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class VersionDomainPersistenceAdapterHistoryRepository
		extends
		AbstractDomainPersistenceAdapterHistoryJpaRepository<Long, UUID, VersionDomainPersistenceAdapterHistoryModel>
{
	public VersionDomainPersistenceAdapterHistoryRepository(
			final VersionDomainPersistenceAdapterHistoryJpaRepository repository)
	{
		super(repository);
	}
}