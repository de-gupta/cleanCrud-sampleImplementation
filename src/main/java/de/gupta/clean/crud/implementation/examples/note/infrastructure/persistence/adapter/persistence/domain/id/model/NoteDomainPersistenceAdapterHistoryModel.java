package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterHistoryModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "note_domain_persistence_adapter_model_history",
		indexes = {
				@Index(name = "note_domain_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "note_domain_persistence_history_idx_persistence_id", columnList = "persistence_id"),
				@Index(name = "note_domain_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class NoteDomainPersistenceAdapterHistoryModel
		extends AbstractDomainPersistenceAdapterHistoryModel<Long, UUID>
		implements DomainPersistenceAdapterHistoryModel<Long, UUID>
{
	public static DomainPersistenceAdapterHistoryModel.Builder<Long, UUID, NoteDomainPersistenceAdapterHistoryModel> builder()
	{
		return new Builder();
	}

	protected NoteDomainPersistenceAdapterHistoryModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, NoteDomainPersistenceAdapterHistoryModel>
			implements
			DomainPersistenceAdapterHistoryModel.Builder<Long, UUID, NoteDomainPersistenceAdapterHistoryModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<NoteDomainPersistenceAdapterHistoryModel>
	{
		@Override
		protected NoteDomainPersistenceAdapterHistoryModel doBuild()
		{
			return (NoteDomainPersistenceAdapterHistoryModel) model;
		}

		private Builder()
		{
			super(new NoteDomainPersistenceAdapterHistoryModel());
		}
	}
}