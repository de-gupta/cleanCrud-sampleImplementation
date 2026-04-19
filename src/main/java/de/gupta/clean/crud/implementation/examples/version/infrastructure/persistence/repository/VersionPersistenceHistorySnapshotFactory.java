package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.AbstractPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public final class VersionPersistenceHistorySnapshotFactory
		extends
		AbstractPersistenceHistorySnapshotFactory<UUID, VersionPersistenceModel, VersionPersistenceModelHistory>
		implements
		TriTemporalHistorySnapshotFactory<UUID, VersionPersistenceModel, VersionPersistenceModelHistory>
{
	@Override
	public VersionPersistenceModelHistory snapshotOf(
			final VersionPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		return VersionPersistenceModelHistory.snapshotOf(model, changeType, decisionTime, validFrom, validTo);
	}
}