package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
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
@Table(name = "version_persistence_model_history",
		indexes = {
				@Index(name = "version_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "version_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class VersionPersistenceModelHistory extends AbstractTriTemporalHistoryModel<UUID>
		implements TriTemporalHistoryModel<UUID>
{
	@Column(name = "version")
	private long version;

	static VersionPersistenceModelHistory snapshotOf(
			final VersionPersistenceModel model,
			final TemporalChangeType changeType,
			final Instant decisionTime,
			final Instant validFrom,
			final Instant validTo)
	{
		var snapshot = new VersionPersistenceModelHistory();
		snapshot.setEntityID(model.id());
		snapshot.setChangeType(changeType);
		snapshot.setDecisionTime(decisionTime);
		snapshot.setValidFrom(validFrom);
		snapshot.setValidTo(validTo);
		snapshot.version = model.version();
		return snapshot;
	}

	protected VersionPersistenceModelHistory()
	{
		super();
	}
}