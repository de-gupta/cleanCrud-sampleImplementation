package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tag_persistence_model")
public class TagPersistenceModelImpl implements TagPersistenceModel
{
	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	static TagPersistenceModelBuilder builder()
	{
		return new TagPersistenceModelBuilderImpl();
	}

	@Override
	public String name()
	{
		return name;
	}

	@Override
	public void setName(final String name)
	{
		this.name = name;
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
		if (!(o instanceof final TagPersistenceModelImpl that)) return false;
		return this == that || Objects.equals(id, that.id);
	}

	protected TagPersistenceModelImpl()
	{
	}

	private static final class TagPersistenceModelBuilderImpl extends AbstractModelBuilder<TagPersistenceModel>
			implements TagPersistenceModelBuilder
	{
		private final TagPersistenceModelImpl model;

		@Override
		public TagPersistenceModelBuilder withName(final String name)
		{
			model.name = name;
			return this;
		}

		@Override
		protected TagPersistenceModel doBuild()
		{
			return model;
		}

		private TagPersistenceModelBuilderImpl()
		{
			this.model = new TagPersistenceModelImpl();
		}
	}
}