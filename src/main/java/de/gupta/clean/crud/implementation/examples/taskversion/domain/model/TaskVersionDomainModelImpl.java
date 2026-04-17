package de.gupta.clean.crud.implementation.examples.taskversion.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;

import java.util.Objects;

final class TaskVersionDomainModelImpl implements TaskVersionDomainModel
{
	private long version;

	static TaskVersionDomainModelBuilder builder()
	{
		return new BuilderImpl();
	}

	@Override
	public long version()
	{
		return version;
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode(version);
	}

	@Override
	public boolean equals(final Object o)
	{
		return o == this ||
				(o instanceof TaskVersionDomainModel that
						&& Objects.equals(version, that.version())
				);
	}

	private TaskVersionDomainModelImpl()
	{
	}

	private static final class BuilderImpl extends AbstractModelBuilder<TaskVersionDomainModel>
			implements TaskVersionDomainModelBuilder
	{
		private final TaskVersionDomainModelImpl model;

		@Override
		public TaskVersionDomainModelBuilder withVersion(final long version)
		{
			model.version = version;
			return this;
		}

		@Override
		protected TaskVersionDomainModel doBuild()
		{
			return model;
		}

		private BuilderImpl()
		{
			this.model = new TaskVersionDomainModelImpl();
		}
	}
}