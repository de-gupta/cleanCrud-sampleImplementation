package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "taskversion_domain_persistence_adapter_model",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "domain_id"),
				@UniqueConstraint(columnNames = "persistence_id")
		},
		indexes = {
				@Index(name = "taskversion_idx_domain_id", columnList = "domain_id"),
				@Index(name = "taskversion_idx_persistence_id", columnList = "persistence_id")
		})
public class TaskVersionDomainPersistenceAdapterModel extends AbstractDomainPersistenceAdapterModel<Long, UUID>
		implements DomainPersistenceAdapterModel<Long, UUID>
{
	public static DomainPersistenceAdapterModel.Builder<Long, UUID, TaskVersionDomainPersistenceAdapterModel> builder()
	{
		return new Builder();
	}

	protected TaskVersionDomainPersistenceAdapterModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, TaskVersionDomainPersistenceAdapterModel>
			implements DomainPersistenceAdapterModel.Builder<Long, UUID, TaskVersionDomainPersistenceAdapterModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<TaskVersionDomainPersistenceAdapterModel>
	{
		@Override
		protected TaskVersionDomainPersistenceAdapterModel doBuild()
		{
			return (TaskVersionDomainPersistenceAdapterModel) model;
		}

		private Builder()
		{
			super(new TaskVersionDomainPersistenceAdapterModel());
		}
	}
}