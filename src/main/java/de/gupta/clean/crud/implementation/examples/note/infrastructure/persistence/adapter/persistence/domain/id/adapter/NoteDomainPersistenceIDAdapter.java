package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model.NoteDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class NoteDomainPersistenceIDAdapter extends AbstractDomainPersistenceIDAdapter<Long, UUID>
		implements DomainPersistenceIDAdapter<Long, UUID>
{
	NoteDomainPersistenceIDAdapter(
			final DomainPersistenceAdapterRepository<Long, UUID, NoteDomainPersistenceAdapterModel> repository)
	{
		super(repository);
	}
}