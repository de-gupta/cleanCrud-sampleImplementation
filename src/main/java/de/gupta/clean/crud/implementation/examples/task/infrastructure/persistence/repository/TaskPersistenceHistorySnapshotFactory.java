package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.AbstractPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
final class TaskPersistenceHistorySnapshotFactory
		extends AbstractPersistenceHistorySnapshotFactory<UUID, TaskPersistenceModel, TaskPersistenceModelHistory>
{
	@Override
	protected TaskPersistenceModelHistory snapshotOf(
			final TaskPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		return TaskPersistenceModelHistory.snapshotOf(model, changeType, decisionTime, validFrom, validTo);
	}
}
