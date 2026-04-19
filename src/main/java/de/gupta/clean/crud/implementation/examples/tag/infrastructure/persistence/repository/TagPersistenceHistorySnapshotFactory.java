package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.AbstractPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public final class TagPersistenceHistorySnapshotFactory
		extends AbstractPersistenceHistorySnapshotFactory<UUID, TagPersistenceModel, TagPersistenceModelHistory>
		implements TriTemporalHistorySnapshotFactory<UUID, TagPersistenceModel, TagPersistenceModelHistory>
{
	@Override
	public TagPersistenceModelHistory snapshotOf(
			final TagPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		return TagPersistenceModelHistory.snapshotOf(model, changeType, decisionTime, validFrom, validTo);
	}
}