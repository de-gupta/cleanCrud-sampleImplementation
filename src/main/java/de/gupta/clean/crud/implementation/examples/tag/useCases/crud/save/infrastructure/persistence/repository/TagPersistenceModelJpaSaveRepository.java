package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.save.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TagPersistenceModelJpaSaveRepository
		extends AbstractPersistenceModelJpaSaveRepository<TagPersistenceModel, UUID, TagPersistenceModelImpl,
		TagPersistenceModelHistory>
		implements SavePersistenceModelRepository<TagPersistenceModel>
{
	TagPersistenceModelJpaSaveRepository(
			final TagJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TagPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TagPersistenceModel, TagPersistenceModelHistory> snapshotFactory,
			@Qualifier("tagAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
