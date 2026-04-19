package de.gupta.clean.crud.implementation.examples.note.useCases.crud.save.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class NotePersistenceModelJpaSaveRepository
		extends AbstractPersistenceModelJpaSaveRepository<NotePersistenceModel, UUID, NotePersistenceModelImpl,
		NotePersistenceModelHistory>
		implements SavePersistenceModelRepository<NotePersistenceModel>
{
	NotePersistenceModelJpaSaveRepository(
			final NoteJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, NotePersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, NotePersistenceModel, NotePersistenceModelHistory> snapshotFactory,
			@Qualifier("noteAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
