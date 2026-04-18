package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "version_domain_persistence_adapter_model",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "domain_id"),
				@UniqueConstraint(columnNames = "persistence_id")
		},
		indexes = {
				@Index(name = "version_idx_domain_id", columnList = "domain_id"),
				@Index(name = "version_idx_persistence_id", columnList = "persistence_id")
		})
public class VersionDomainPersistenceAdapterModel extends AbstractDomainPersistenceAdapterModel<Long, UUID>
		implements DomainPersistenceAdapterModel<Long, UUID>
{
	public static DomainPersistenceAdapterModel.Builder<Long, UUID, VersionDomainPersistenceAdapterModel> builder()
	{
		return new Builder();
	}

	protected VersionDomainPersistenceAdapterModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, VersionDomainPersistenceAdapterModel>
			implements DomainPersistenceAdapterModel.Builder<Long, UUID, VersionDomainPersistenceAdapterModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<VersionDomainPersistenceAdapterModel>
	{
		@Override
		protected VersionDomainPersistenceAdapterModel doBuild()
		{
			return (VersionDomainPersistenceAdapterModel) model;
		}

		private Builder()
		{
			super(new VersionDomainPersistenceAdapterModel());
		}
	}
}