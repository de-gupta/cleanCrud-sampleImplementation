package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model.VersionDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model.VersionDomainPersistenceAdapterModel;
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
final class VersionDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, VersionDomainPersistenceAdapterModel,
		VersionDomainPersistenceAdapterHistoryModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	VersionDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, VersionDomainPersistenceAdapterModel> repository,
			final TriTemporalHistoryRepository<Long, VersionDomainPersistenceAdapterHistoryModel> historyRepository,
			@Qualifier("versionLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator,
			@Qualifier("versionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(repository,
				BuilderFactories.of(VersionDomainPersistenceAdapterModel::builder),
				historyRepository,
				BuilderFactories.of(VersionDomainPersistenceAdapterHistoryModel::builder),
				domainIDGenerator, auditActorSupplier);
	}
}