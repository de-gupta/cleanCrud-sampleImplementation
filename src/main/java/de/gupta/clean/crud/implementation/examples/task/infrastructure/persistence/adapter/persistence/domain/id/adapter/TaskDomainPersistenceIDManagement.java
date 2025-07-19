package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model.TaskDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, TaskDomainPersistenceAdapterModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	TaskDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, TaskDomainPersistenceAdapterModel> repository,
			final ModelBuilderFactory<DomainPersistenceAdapterModel<Long, UUID>, DomainPersistenceAdapterModel.Builder<Long, UUID, TaskDomainPersistenceAdapterModel>> modelBuilderFactory,
			@Qualifier("taskLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator)
	{
		super(repository, modelBuilderFactory, domainIDGenerator);
	}
}