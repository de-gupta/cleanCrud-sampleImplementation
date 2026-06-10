package de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.domain;

import de.gupta.clean.crud.template.useCases.incantation.domain.model.ApplicationIncantationPayload;

import java.util.Objects;

public record RegisterTagIncantation(String name) implements ApplicationIncantationPayload
{
	public RegisterTagIncantation
	{
		Objects.requireNonNull(name, "name");
	}
}
