package de.gupta.clean.crud.implementation.examples.version.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.domain.model.validation.Validatable;

public interface VersionModel extends Validatable
{
	long version();

	@Override
	default void validate()
	{
	}

	interface VersionModelBuilder<M extends VersionModel, B extends VersionModelBuilder<M, B>>
			extends ModelBuilder<M>
	{
		B withVersion(final long version);
	}
}