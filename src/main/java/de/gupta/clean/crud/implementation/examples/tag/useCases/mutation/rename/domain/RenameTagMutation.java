package de.gupta.clean.crud.implementation.examples.tag.useCases.mutation.rename.domain;

import de.gupta.clean.crud.template.useCases.mutation.domain.model.ApplicationMutationPayload;

import java.util.Objects;

public record RenameTagMutation(String name) implements ApplicationMutationPayload
{
	public RenameTagMutation
	{
		name = Objects.requireNonNull(name, "name").trim();
	}
}
