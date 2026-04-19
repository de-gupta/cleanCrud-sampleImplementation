package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
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
@Table(name = "tag_persistence_model_history",
		indexes = {
				@Index(name = "tag_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "tag_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class TagPersistenceModelHistory extends AbstractTriTemporalHistoryModel<UUID>
		implements TriTemporalHistoryModel<UUID>
{
	@Column(name = "name")
	private String name;

	static TagPersistenceModelHistory snapshotOf(
			final TagPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		var snapshot = new TagPersistenceModelHistory();
		snapshot.setEntityID(model.id());
		snapshot.setChangeType(changeType);
		snapshot.setDecisionTime(decisionTime);
		snapshot.setValidFrom(validFrom);
		snapshot.setValidTo(validTo);
		snapshot.name = model.name();
		return snapshot;
	}

	protected TagPersistenceModelHistory()
	{
		super();
	}
}