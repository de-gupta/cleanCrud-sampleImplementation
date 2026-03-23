package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterHistoryModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "task_domain_persistence_adapter_model_history",
		indexes = {
				@Index(name = "task_domain_persistence_history_idx_entity_id", columnList = "entity_id"),
				@Index(name = "task_domain_persistence_history_idx_persistence_id", columnList = "persistence_id"),
				@Index(name = "task_domain_persistence_history_idx_validity", columnList = "valid_from, valid_to")
		})
public class TaskDomainPersistenceAdapterHistoryModel
		extends AbstractDomainPersistenceAdapterHistoryModel<Long, UUID>
		implements DomainPersistenceAdapterHistoryModel<Long, UUID>
{
	public static DomainPersistenceAdapterHistoryModel.Builder<Long, UUID,
			TaskDomainPersistenceAdapterHistoryModel> builder()
	{
		return new Builder();
	}

	protected TaskDomainPersistenceAdapterHistoryModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, TaskDomainPersistenceAdapterHistoryModel>
			implements DomainPersistenceAdapterHistoryModel.Builder<Long, UUID,
			TaskDomainPersistenceAdapterHistoryModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<TaskDomainPersistenceAdapterHistoryModel>
	{
		@Override
		protected TaskDomainPersistenceAdapterHistoryModel doBuild()
		{
			return (TaskDomainPersistenceAdapterHistoryModel) model;
		}

		private Builder()
		{
			super(new TaskDomainPersistenceAdapterHistoryModel());
		}
	}
}