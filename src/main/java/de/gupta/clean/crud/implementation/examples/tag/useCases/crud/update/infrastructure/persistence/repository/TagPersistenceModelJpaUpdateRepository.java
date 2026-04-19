package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TagPersistenceModelJpaUpdateRepository
		extends AbstractPersistenceModelJpaUpdateRepository<TagPersistenceModel, UUID, TagPersistenceModelImpl,
		TagPersistenceModelHistory>
		implements UpdatePersistenceModelRepository<TagPersistenceModel>
{
	public TagPersistenceModelJpaUpdateRepository(
			final TagJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TagPersistenceModelHistory> historyRepository,
			final TagPersistenceHistorySnapshotFactory snapshotFactory,
			@Qualifier("tagAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
