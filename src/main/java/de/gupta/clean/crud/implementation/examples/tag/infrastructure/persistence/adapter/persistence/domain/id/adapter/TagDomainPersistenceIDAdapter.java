package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model.TagDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TagDomainPersistenceIDAdapter extends AbstractDomainPersistenceIDAdapter<Long, UUID>
		implements DomainPersistenceIDAdapter<Long, UUID>
{
	TagDomainPersistenceIDAdapter(
			final DomainPersistenceAdapterRepository<Long, UUID, TagDomainPersistenceAdapterModel> repository)
	{
		super(repository);
	}
}