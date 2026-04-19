package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto;

import jakarta.validation.constraints.NotNull;

public record VersionAPIModelCreate(
		@NotNull(message = "Version is required")
		long version
)
{
}