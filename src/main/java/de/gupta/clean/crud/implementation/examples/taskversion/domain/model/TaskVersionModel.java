package de.gupta.clean.crud.implementation.examples.taskversion.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.domain.model.validation.Validatable;

public interface TaskVersionModel extends Validatable
{
	long version();

	@Override
	default void validate()
	{
	}

	interface TaskVersionModelBuilder<M extends TaskVersionModel, B extends TaskVersionModelBuilder<M, B>>
			extends ModelBuilder<M>
	{
		B withVersion(final long version);
	}
}