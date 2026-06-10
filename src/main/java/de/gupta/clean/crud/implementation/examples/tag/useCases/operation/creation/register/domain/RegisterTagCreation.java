package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain;

import de.gupta.clean.crud.template.useCases.operation.domain.model.ApplicationOperationPayload;

import java.util.Objects;

public record RegisterTagCreation(String name) implements ApplicationOperationPayload
{
	public RegisterTagCreation
	{
		Objects.requireNonNull(name, "name");
	}
}
