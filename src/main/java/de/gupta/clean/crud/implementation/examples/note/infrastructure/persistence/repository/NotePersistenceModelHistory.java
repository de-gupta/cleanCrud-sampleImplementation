package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
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
@Table(name = "note_persistence_model_history",
		indexes = {
				@Index(name = "note_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "note_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class NotePersistenceModelHistory extends AbstractTriTemporalHistoryModel<UUID>
		implements TriTemporalHistoryModel<UUID>
{
	@Column(name = "note")
	private String note;

	static NotePersistenceModelHistory snapshotOf(
			final NotePersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		var snapshot = new NotePersistenceModelHistory();
		snapshot.setEntityID(model.id());
		snapshot.setChangeType(changeType);
		snapshot.setDecisionTime(decisionTime);
		snapshot.setValidFrom(validFrom);
		snapshot.setValidTo(validTo);
		snapshot.note = model.note();
		return snapshot;
	}

	protected NotePersistenceModelHistory()
	{
		super();
	}
}