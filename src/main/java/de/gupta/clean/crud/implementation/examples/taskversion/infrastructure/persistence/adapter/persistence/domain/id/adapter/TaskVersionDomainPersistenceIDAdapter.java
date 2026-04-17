package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model.TaskVersionDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskVersionDomainPersistenceIDAdapter extends AbstractDomainPersistenceIDAdapter<Long, UUID>
		implements DomainPersistenceIDAdapter<Long, UUID>
{
	TaskVersionDomainPersistenceIDAdapter(
			final DomainPersistenceAdapterRepository<Long, UUID, TaskVersionDomainPersistenceAdapterModel> repository)
	{
		super(repository);
	}
}