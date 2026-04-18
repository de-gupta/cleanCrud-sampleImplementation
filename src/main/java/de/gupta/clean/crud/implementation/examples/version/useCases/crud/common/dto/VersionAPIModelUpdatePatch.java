package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto;

import java.util.Optional;

public record VersionAPIModelUpdatePatch(
		Optional<Long> id,
		Optional<Long> version
)
{
}