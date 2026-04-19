package de.gupta.clean.crud.implementation.examples.note.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotePersistenceModelJpaUpdateRepository
		extends AbstractPersistenceModelJpaUpdateRepository<NotePersistenceModel, UUID, NotePersistenceModelImpl,
		NotePersistenceModelHistory>
		implements UpdatePersistenceModelRepository<NotePersistenceModel>
{
	public NotePersistenceModelJpaUpdateRepository(
			final NoteJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, NotePersistenceModelHistory> historyRepository,
			final NotePersistenceHistorySnapshotFactory snapshotFactory,
			@Qualifier("noteAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}
