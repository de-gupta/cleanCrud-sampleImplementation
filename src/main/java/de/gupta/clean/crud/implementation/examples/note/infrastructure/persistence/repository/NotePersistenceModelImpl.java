package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "note_persistence_model")
public class NotePersistenceModelImpl implements NotePersistenceModel
{
	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(name = "note", nullable = false)
	private String note;

	static NotePersistenceModelBuilder builder()
	{
		return new NotePersistenceModelBuilderImpl();
	}

	@Override
	public String note()
	{
		return note;
	}

	@Override
	public void setNote(final String note)
	{
		this.note = note;
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
		if (!(o instanceof final NotePersistenceModelImpl that)) return false;
		return this == that || Objects.equals(id, that.id);
	}

	protected NotePersistenceModelImpl()
	{
	}

	private static final class NotePersistenceModelBuilderImpl extends AbstractModelBuilder<NotePersistenceModel>
			implements NotePersistenceModelBuilder
	{
		private final NotePersistenceModelImpl model;

		@Override
		public NotePersistenceModelBuilder withNote(final String note)
		{
			model.note = note;
			return this;
		}

		@Override
		protected NotePersistenceModel doBuild()
		{
			return model;
		}

		private NotePersistenceModelBuilderImpl()
		{
			this.model = new NotePersistenceModelImpl();
		}
	}
}