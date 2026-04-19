package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "tag_domain_persistence_adapter_model",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "domain_id"),
				@UniqueConstraint(columnNames = "persistence_id")
		},
		indexes = {
				@Index(name = "tag_idx_domain_id", columnList = "domain_id"),
				@Index(name = "tag_idx_persistence_id", columnList = "persistence_id")
		})
public class TagDomainPersistenceAdapterModel extends AbstractDomainPersistenceAdapterModel<Long, UUID>
		implements DomainPersistenceAdapterModel<Long, UUID>
{
	public static DomainPersistenceAdapterModel.Builder<Long, UUID, TagDomainPersistenceAdapterModel> builder()
	{
		return new Builder();
	}

	protected TagDomainPersistenceAdapterModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, TagDomainPersistenceAdapterModel>
			implements DomainPersistenceAdapterModel.Builder<Long, UUID, TagDomainPersistenceAdapterModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<TagDomainPersistenceAdapterModel>
	{
		@Override
		protected TagDomainPersistenceAdapterModel doBuild()
		{
			return (TagDomainPersistenceAdapterModel) model;
		}

		private Builder()
		{
			super(new TagDomainPersistenceAdapterModel());
		}
	}
}