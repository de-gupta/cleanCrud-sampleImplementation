package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model.VersionDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class VersionDomainPersistenceIDAdapter extends AbstractDomainPersistenceIDAdapter<Long, UUID>
		implements DomainPersistenceIDAdapter<Long, UUID>
{
	VersionDomainPersistenceIDAdapter(
			final DomainPersistenceAdapterRepository<Long, UUID, VersionDomainPersistenceAdapterModel> repository)
	{
		super(repository);
	}
}