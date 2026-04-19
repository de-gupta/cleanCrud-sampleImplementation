package de.gupta.clean.crud.implementation.examples.tag.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.domain.model.validation.Validatable;

public interface TagModel extends Validatable
{
	String name();

	@Override
	default void validate()
	{
	}

	interface TagModelBuilder<M extends TagModel, B extends TagModelBuilder<M, B>> extends ModelBuilder<M>
	{
		B withName(final String name);
	}
}