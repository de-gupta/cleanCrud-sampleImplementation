package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "taskversion_persistence_model")
public class TaskVersionPersistenceModelImpl implements TaskVersionPersistenceModel
{
	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(name = "version", nullable = false)
	private long version;

	static TaskVersionPersistenceModelBuilder builder()
	{
		return new TaskVersionPersistenceModelBuilderImpl();
	}

	@Override
	public long version()
	{
		return version;
	}

	@Override
	public void setVersion(final long version)
	{
		this.version = version;
		this.validate();
	}

	@Override
	public UUID id()
	{
		return id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object o)
	{
		if (!(o instanceof final TaskVersionPersistenceModelImpl that)) return false;
		return this == that || Objects.equals(id, that.id);
	}

	protected TaskVersionPersistenceModelImpl()
	{
	}

	private static final class TaskVersionPersistenceModelBuilderImpl
			extends AbstractModelBuilder<TaskVersionPersistenceModel>
			implements TaskVersionPersistenceModelBuilder
	{
		private final TaskVersionPersistenceModelImpl model;

		@Override
		public TaskVersionPersistenceModelBuilder withVersion(final long version)
		{
			model.version = version;
			return this;
		}

		@Override
		protected TaskVersionPersistenceModel doBuild()
		{
			return model;
		}

		private TaskVersionPersistenceModelBuilderImpl()
		{
			this.model = new TaskVersionPersistenceModelImpl();
		}
	}
}