package de.gupta.clean.crud.implementation.examples.version.useCases.crud.save.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class VersionPersistenceModelJpaSaveRepository
		extends
		AbstractPersistenceModelJpaSaveRepository<VersionPersistenceModel, UUID, VersionPersistenceModelImpl,
				VersionPersistenceModelHistory>
		implements SavePersistenceModelRepository<VersionPersistenceModel>
{
	VersionPersistenceModelJpaSaveRepository(
			final VersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, VersionPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, VersionPersistenceModel, VersionPersistenceModelHistory> snapshotFactory,
			@Qualifier("versionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}