package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto;

import jakarta.validation.constraints.NotNull;

public record TaskVersionAPIModelCreate(
		@NotNull(message = "Version is required")
		long version
)
{
}