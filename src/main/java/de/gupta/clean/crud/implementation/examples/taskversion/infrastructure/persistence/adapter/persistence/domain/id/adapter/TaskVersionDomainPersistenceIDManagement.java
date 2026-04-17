package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model.TaskVersionDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model.TaskVersionDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.domain.model.builder.BuilderFactories;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskVersionDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, TaskVersionDomainPersistenceAdapterModel,
		TaskVersionDomainPersistenceAdapterHistoryModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	TaskVersionDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, TaskVersionDomainPersistenceAdapterModel> repository,
			final TriTemporalHistoryRepository<Long, TaskVersionDomainPersistenceAdapterHistoryModel> historyRepository,
			@Qualifier("taskVersionLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator,
			@Qualifier("taskVersionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(repository,
				BuilderFactories.of(TaskVersionDomainPersistenceAdapterModel::builder),
				historyRepository,
				BuilderFactories.of(TaskVersionDomainPersistenceAdapterHistoryModel::builder),
				domainIDGenerator, auditActorSupplier);
	}
}
