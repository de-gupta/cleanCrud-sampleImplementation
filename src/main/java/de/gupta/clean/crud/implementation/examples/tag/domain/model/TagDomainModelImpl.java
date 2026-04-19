package de.gupta.clean.crud.implementation.examples.tag.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;

import java.util.Objects;

final class TagDomainModelImpl implements TagDomainModel
{
	private String name;

	static TagDomainModelBuilder builder()
	{
		return new BuilderImpl();
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result = 31 * result + Objects.hashCode(name);
		return result;
	}

	@Override
	public boolean equals(final Object o)
	{
		return o == this ||
				(o instanceof TagDomainModel that
						&& Objects.equals(name, that.name())
				);
	}

	private TagDomainModelImpl()
	{
	}

	private static final class BuilderImpl extends AbstractModelBuilder<TagDomainModel>
			implements TagDomainModelBuilder
	{
		private final TagDomainModelImpl model;

		@Override
		public TagDomainModelBuilder withName(final String name)
		{
			model.name = name;
			return this;
		}

		@Override
		protected TagDomainModel doBuild()
		{
			return model;
		}

		private BuilderImpl()
		{
			this.model = new TagDomainModelImpl();
		}
	}
}