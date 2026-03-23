package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "task_domain_persistence_adapter_model",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "domain_id"),
				@UniqueConstraint(columnNames = "persistence_id")
		},
		indexes = {
				@Index(name = "task_idx_domain_id", columnList = "domain_id"),
				@Index(name = "task_idx_persistence_id", columnList = "persistence_id")
		})
public class TaskDomainPersistenceAdapterModel extends AbstractDomainPersistenceAdapterModel<Long, UUID>
		implements DomainPersistenceAdapterModel<Long, UUID>
{
	public static DomainPersistenceAdapterModel.Builder<Long, UUID, TaskDomainPersistenceAdapterModel> builder()
	{
		return new Builder();
	}

	protected TaskDomainPersistenceAdapterModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, TaskDomainPersistenceAdapterModel>
			implements DomainPersistenceAdapterModel.Builder<Long, UUID, TaskDomainPersistenceAdapterModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<TaskDomainPersistenceAdapterModel>
	{
		@Override
		protected TaskDomainPersistenceAdapterModel doBuild()
		{
			return (TaskDomainPersistenceAdapterModel) model;
		}

		private Builder()
		{
			super(new TaskDomainPersistenceAdapterModel());
		}
	}
}