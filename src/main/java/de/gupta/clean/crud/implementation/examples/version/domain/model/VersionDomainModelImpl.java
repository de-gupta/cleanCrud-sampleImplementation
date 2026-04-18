package de.gupta.clean.crud.implementation.examples.version.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;

import java.util.Objects;

final class VersionDomainModelImpl implements VersionDomainModel
{
	private long version;

	static VersionDomainModelBuilder builder()
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
				(o instanceof VersionDomainModel that
						&& Objects.equals(version, that.version())
				);
	}

	private VersionDomainModelImpl()
	{
	}

	private static final class BuilderImpl extends AbstractModelBuilder<VersionDomainModel>
			implements VersionDomainModelBuilder
	{
		private final VersionDomainModelImpl model;

		@Override
		public VersionDomainModelBuilder withVersion(final long version)
		{
			model.version = version;
			return this;
		}

		@Override
		protected VersionDomainModel doBuild()
		{
			return model;
		}

		private BuilderImpl()
		{
			this.model = new VersionDomainModelImpl();
		}
	}
}