package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.domain.model.validation.Validatable;

import java.util.Optional;

public interface TaskModel extends Validatable
{
	String title();

	Optional<String> description();

	@Override
	default void validate()
	{
	}

	interface TaskModelBuilder<M extends TaskModel, B extends TaskModelBuilder<M, B>> extends ModelBuilder<M>
	{
		B withTitle(String title);

		B withDescription(Optional<String> description);
	}
}