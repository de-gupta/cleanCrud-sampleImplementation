package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.AbstractTriTemporalHistoryModel;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TemporalChangeType;
import de.gupta.clean.crud.template.infrastructure.persistence.history.model.TriTemporalHistoryModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "task_persistence_model_history",
		indexes = {
				@Index(name = "task_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "task_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class TaskPersistenceModelHistory extends AbstractTriTemporalHistoryModel<UUID> implements
		TriTemporalHistoryModel<UUID>
{
	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	static TaskPersistenceModelHistory snapshotOf(
			final TaskPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		var snapshot = new TaskPersistenceModelHistory();
		snapshot.setEntityID(model.id());
		snapshot.setChangeType(changeType);
		snapshot.setDecisionTime(decisionTime);
		snapshot.setValidFrom(validFrom);
		snapshot.setValidTo(validTo);
		snapshot.title = model.title();
		snapshot.description = model.description().orElse(null);
		return snapshot;
	}

	protected TaskPersistenceModelHistory()
	{
		super();
	}
}