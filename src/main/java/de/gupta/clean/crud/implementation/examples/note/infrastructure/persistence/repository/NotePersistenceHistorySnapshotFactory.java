package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.AbstractPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public final class NotePersistenceHistorySnapshotFactory
		extends AbstractPersistenceHistorySnapshotFactory<UUID, NotePersistenceModel, NotePersistenceModelHistory>
		implements TriTemporalHistorySnapshotFactory<UUID, NotePersistenceModel, NotePersistenceModelHistory>
{
	@Override
	public NotePersistenceModelHistory snapshotOf(
			final NotePersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		return NotePersistenceModelHistory.snapshotOf(model, changeType, decisionTime, validFrom, validTo);
	}
}