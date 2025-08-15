package de.gupta.clean.crud.implementation.examples.task.domain.model.validation;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskModel;
import de.gupta.clean.crud.template.domain.model.exceptions.validation.FieldValidationFailedException;
import de.gupta.clean.crud.template.domain.model.exceptions.validation.RequiredFieldNotSetException;
import de.gupta.validation.aegis.api.validation.Validation;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import static de.gupta.validation.aegis.api.validation.factories.ObjectValidations.notNullSpecification;
import static de.gupta.validation.aegis.api.validation.factories.StringValidations.trimmedStringSpecification;

public final class TaskModelValidationSupplier<T extends TaskModel> implements Supplier<Validation<T>>
{
	private final Function<T, String> titleExtractor = TaskModel::title;
	private final Function<T, Optional<String>> descriptionExtractor = TaskModel::description;

	public static <T extends TaskModel> TaskModelValidationSupplier<T> create()
	{
		return new TaskModelValidationSupplier<>();
	}

	@Override
	public Validation<T> get()
	{
		return notNullSpecification(titleExtractor, RequiredFieldNotSetException.forMessage("Title is required"))
				.and(trimmedStringSpecification(titleExtractor,
						FieldValidationFailedException.forMessage("Title may not have leading or trailing whitespaces")
				));
	}

	private TaskModelValidationSupplier()
	{
	}
}