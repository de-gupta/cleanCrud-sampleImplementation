package de.gupta.clean.crud.implementation.examples.version.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VersionPersistenceModelJpaUpdateRepository
		extends
		AbstractPersistenceModelJpaUpdateRepository<VersionPersistenceModel, UUID, VersionPersistenceModelImpl,
				VersionPersistenceModelHistory>
		implements UpdatePersistenceModelRepository<VersionPersistenceModel>
{
	public VersionPersistenceModelJpaUpdateRepository(
			final VersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, VersionPersistenceModelHistory> historyRepository,
			final VersionPersistenceHistorySnapshotFactory snapshotFactory,
			@Qualifier("versionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}