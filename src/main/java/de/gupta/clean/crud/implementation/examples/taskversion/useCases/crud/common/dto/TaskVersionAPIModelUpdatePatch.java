package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto;

import java.util.Optional;

public record TaskVersionAPIModelUpdatePatch(
		Optional<Long> id,
		Optional<Long> version
)
{
}
