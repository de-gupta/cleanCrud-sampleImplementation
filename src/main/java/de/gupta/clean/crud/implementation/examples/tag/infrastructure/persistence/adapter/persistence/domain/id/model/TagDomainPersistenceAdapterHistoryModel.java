package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterHistoryModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tag_domain_persistence_adapter_model_history",
		indexes = {
				@Index(name = "tag_domain_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "tag_domain_persistence_history_idx_persistence_id", columnList = "persistence_id"),
				@Index(name = "tag_domain_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class TagDomainPersistenceAdapterHistoryModel
		extends AbstractDomainPersistenceAdapterHistoryModel<Long, UUID>
		implements DomainPersistenceAdapterHistoryModel<Long, UUID>
{
	public static DomainPersistenceAdapterHistoryModel.Builder<Long, UUID, TagDomainPersistenceAdapterHistoryModel> builder()
	{
		return new Builder();
	}

	protected TagDomainPersistenceAdapterHistoryModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, TagDomainPersistenceAdapterHistoryModel>
			implements
			DomainPersistenceAdapterHistoryModel.Builder<Long, UUID, TagDomainPersistenceAdapterHistoryModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<TagDomainPersistenceAdapterHistoryModel>
	{
		@Override
		protected TagDomainPersistenceAdapterHistoryModel doBuild()
		{
			return (TagDomainPersistenceAdapterHistoryModel) model;
		}

		private Builder()
		{
			super(new TagDomainPersistenceAdapterHistoryModel());
		}
	}
}