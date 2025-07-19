package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;

import java.util.Optional;

final class TaskDomainModelImpl implements TaskDomainModel
{
	private String title;
	private String description;

	static TaskDomainModelBuilder builder()
	{
		return new BuilderImpl();
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
	public int hashCode()
	{
		return title == null ? 0 : title.trim().toLowerCase().hashCode();
	}

	@Override
	public boolean equals(Object o)
	{
		return o instanceof final TaskDomainModel that &&
				(this == that || title != null && that.title() != null && title.trim()
																			   .equalsIgnoreCase(that.title().trim()));
	}

	private TaskDomainModelImpl()
	{
	}

	private static final class BuilderImpl extends AbstractModelBuilder<TaskDomainModel>
			implements
			TaskDomainModelBuilder
	{
		private final TaskDomainModelImpl model;

		@Override
		public TaskDomainModelBuilder withTitle(final String title)
		{
			model.title = title;
			return this;
		}

		@Override
		public TaskDomainModelBuilder withDescription(
				final Optional<String> description)
		{
			description.ifPresent(d -> model.description = d);
			return this;
		}

		@Override
		protected TaskDomainModel doBuild()
		{
			return model;
		}

		private BuilderImpl()
		{
			this.model = new TaskDomainModelImpl();
		}
	}
}