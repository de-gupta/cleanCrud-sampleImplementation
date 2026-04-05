package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "task_persistence_model")
public class TaskPersistenceModelImpl implements TaskPersistenceModel
{
	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(nullable = false)
	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;

	static TaskPersistenceModelBuilder builder()
	{
		return new TaskPersistenceModelBuilderImpl();
	}

	@Override
	public String title()
	{
		return title;
	}

	@Override
	public Optional<String> description()
	{
		return Optional.ofNullable(description);
	}

	@Override
	public UUID id()
	{
		return id;
	}

	@Override
	public void setTitle(final String title)
	{
		this.title = title;
		this.validate();
	}

	@Override
	public void setDescription(final String description)
	{
		this.description = description;
		this.validate();
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object o)
	{
		if (!(o instanceof final TaskPersistenceModelImpl that)) return false;
		return this == that || Objects.equals(id, that.id);
	}

	protected TaskPersistenceModelImpl()
	{
	}

	private static final class TaskPersistenceModelBuilderImpl extends AbstractModelBuilder<TaskPersistenceModel>
			implements TaskPersistenceModelBuilder
	{
		private final TaskPersistenceModelImpl model;

		@Override
		public TaskPersistenceModelBuilder withTitle(final String title)
		{
			model.title = title;
			return this;
		}

		@Override
		public TaskPersistenceModelBuilder withDescription(final Optional<String> description)
		{
			model.description = description.orElse(null);
			return this;
		}

		@Override
		protected TaskPersistenceModel doBuild()
		{
			return model;
		}

		private TaskPersistenceModelBuilderImpl()
		{
			this.model = new TaskPersistenceModelImpl();
		}
	}
}