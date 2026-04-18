package de.gupta.clean.crud.implementation.examples.note.useCases.crud.delete.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("noteDeletePersistenceModelRepository")
class NotePersistenceModelJpaDeleteRepository
		extends AbstractPersistenceModelJpaDeleteRepository<NotePersistenceModel, UUID, NotePersistenceModelImpl,
		NotePersistenceModelHistory>
		implements DeletePersistenceModelRepository<UUID>
{
	NotePersistenceModelJpaDeleteRepository(
			final NoteJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, NotePersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, NotePersistenceModel, NotePersistenceModelHistory> snapshotFactory,
			final @Qualifier("noteAuditActorSupplier") AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
