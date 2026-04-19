package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto;

import java.util.Optional;

public record NoteAPIModelUpdatePatch(
		Optional<String> note
)
{
	public static NoteAPIModelUpdatePatch of(final Optional<String> note)
	{
		return new NoteAPIModelUpdatePatch(note);
	}
}