package de.gupta.clean.crud.implementation.examples.note.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;

import java.util.Objects;

final class NoteDomainModelImpl implements NoteDomainModel
{
	private String note;

	static NoteDomainModelBuilder builder()
	{
		return new BuilderImpl();
	}

	@Override
	public String note()
	{
		return note;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result = 31 * result + (note != null ? note.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(final Object o)
	{
		return o == this ||
				(o instanceof NoteDomainModel that
						&& Objects.equals(note, that.note())
				);
	}

	private NoteDomainModelImpl()
	{
	}

	private static final class BuilderImpl extends AbstractModelBuilder<NoteDomainModel>
			implements NoteDomainModelBuilder
	{
		private final NoteDomainModelImpl model;

		@Override
		public NoteDomainModelBuilder withNote(final String note)
		{
			model.note = note;
			return this;
		}

		@Override
		protected NoteDomainModel doBuild()
		{
			return model;
		}

		private BuilderImpl()
		{
			this.model = new NoteDomainModelImpl();
		}
	}
}