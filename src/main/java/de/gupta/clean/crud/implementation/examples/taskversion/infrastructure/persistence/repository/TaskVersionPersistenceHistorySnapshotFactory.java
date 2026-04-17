package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.AbstractPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public final class TaskVersionPersistenceHistorySnapshotFactory
		extends
		AbstractPersistenceHistorySnapshotFactory<UUID, TaskVersionPersistenceModel, TaskVersionPersistenceModelHistory>
		implements
		TriTemporalHistorySnapshotFactory<UUID, TaskVersionPersistenceModel, TaskVersionPersistenceModelHistory>
{
	@Override
	public TaskVersionPersistenceModelHistory snapshotOf(
			final TaskVersionPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		return TaskVersionPersistenceModelHistory.snapshotOf(model, changeType, decisionTime, validFrom, validTo);
	}
}