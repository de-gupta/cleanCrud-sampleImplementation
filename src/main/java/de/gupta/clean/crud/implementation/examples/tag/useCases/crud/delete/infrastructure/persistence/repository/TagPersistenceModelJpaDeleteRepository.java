package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.delete.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("tagDeletePersistenceModelRepository")
class TagPersistenceModelJpaDeleteRepository
		extends AbstractPersistenceModelJpaDeleteRepository<TagPersistenceModel, UUID, TagPersistenceModelImpl,
		TagPersistenceModelHistory>
		implements DeletePersistenceModelRepository<UUID>
{
	TagPersistenceModelJpaDeleteRepository(
			final TagJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TagPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TagPersistenceModel, TagPersistenceModelHistory> snapshotFactory,
			final @Qualifier("tagAuditActorSupplier") AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
