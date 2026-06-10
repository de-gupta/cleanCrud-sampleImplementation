package de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.domain;

import de.gupta.clean.crud.template.useCases.operation.domain.model.ApplicationOperationPayload;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record RegisterTaskCreation(
		String title,
		Optional<String> description,
		Optional<Long> version,
		List<String> notes) implements ApplicationOperationPayload
{
	public RegisterTaskCreation
	{
		Objects.requireNonNull(title, "title");
		description = Optional.ofNullable(description).orElse(Optional.empty());
		version = Optional.ofNullable(version).orElse(Optional.empty());
		notes = notes == null ? List.of() : List.copyOf(notes);
	}
}
