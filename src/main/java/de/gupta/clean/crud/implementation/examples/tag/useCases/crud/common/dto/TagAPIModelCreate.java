package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto;

import jakarta.validation.constraints.NotBlank;

public record TagAPIModelCreate(
		@NotBlank(message = "Name is required")
		String name
)
{
	public TagAPIModelCreate
	{
	}
}