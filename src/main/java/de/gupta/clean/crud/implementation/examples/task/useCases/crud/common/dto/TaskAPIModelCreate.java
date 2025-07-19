package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public record TaskAPIModelCreate(
		@NotBlank(message = "Title is required")
		String title,
		Optional<String> description
)
{
}