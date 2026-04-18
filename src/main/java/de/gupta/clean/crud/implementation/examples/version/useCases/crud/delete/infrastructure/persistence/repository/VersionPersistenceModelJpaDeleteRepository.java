package de.gupta.clean.crud.implementation.examples.version.useCases.crud.delete.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("versionDeletePersistenceModelRepository")
class VersionPersistenceModelJpaDeleteRepository
		extends
		AbstractPersistenceModelJpaDeleteRepository<VersionPersistenceModel, UUID, VersionPersistenceModelImpl,
				VersionPersistenceModelHistory>
		implements DeletePersistenceModelRepository<UUID>
{
	VersionPersistenceModelJpaDeleteRepository(
			final VersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, VersionPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, VersionPersistenceModel, VersionPersistenceModelHistory> snapshotFactory,
			final @Qualifier("versionAuditActorSupplier") AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}