package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model.TaskDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskDomainPersistenceIDAdapter extends AbstractDomainPersistenceIDAdapter<Long, UUID>
		implements DomainPersistenceIDAdapter<Long, UUID>
{
	TaskDomainPersistenceIDAdapter(
			final DomainPersistenceAdapterRepository<Long, UUID, TaskDomainPersistenceAdapterModel> repository)
	{
		super(repository);
	}
}