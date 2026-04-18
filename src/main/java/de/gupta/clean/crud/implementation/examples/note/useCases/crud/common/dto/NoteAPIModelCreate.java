package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto;

import jakarta.validation.constraints.NotBlank;

public record NoteAPIModelCreate(
		@NotBlank(message = "Note is required")
		String note
)
{
}