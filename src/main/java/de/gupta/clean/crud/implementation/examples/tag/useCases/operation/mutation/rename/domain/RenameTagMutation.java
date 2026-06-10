package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.domain;

import de.gupta.clean.crud.template.useCases.operation.domain.model.ApplicationOperationPayload;

import java.util.Objects;

public record RenameTagMutation(String name) implements ApplicationOperationPayload
{
	public RenameTagMutation
	{
		name = Objects.requireNonNull(name, "name").trim();
	}
}
