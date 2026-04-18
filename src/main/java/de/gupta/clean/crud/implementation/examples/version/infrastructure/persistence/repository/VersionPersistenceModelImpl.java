package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.AbstractModelBuilder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "version_persistence_model")
public class VersionPersistenceModelImpl implements VersionPersistenceModel
{
	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(name = "version", nullable = false)
	private long version;

	static VersionPersistenceModelBuilder builder()
	{
		return new VersionPersistenceModelBuilderImpl();
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
		if (!(o instanceof final VersionPersistenceModelImpl that)) return false;
		return this == that || Objects.equals(id, that.id);
	}

	protected VersionPersistenceModelImpl()
	{
	}

	private static final class VersionPersistenceModelBuilderImpl
			extends AbstractModelBuilder<VersionPersistenceModel>
			implements VersionPersistenceModelBuilder
	{
		private final VersionPersistenceModelImpl model;

		@Override
		public VersionPersistenceModelBuilder withVersion(final long version)
		{
			model.version = version;
			return this;
		}

		@Override
		protected VersionPersistenceModel doBuild()
		{
			return model;
		}

		private VersionPersistenceModelBuilderImpl()
		{
			this.model = new VersionPersistenceModelImpl();
		}
	}
}