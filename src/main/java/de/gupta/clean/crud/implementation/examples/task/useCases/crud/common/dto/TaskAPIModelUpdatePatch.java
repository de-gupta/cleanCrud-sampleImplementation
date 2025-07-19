package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import java.util.Optional;

public record TaskAPIModelUpdatePatch(
		Optional<String> title,
		Optional<String> description
)
{
}