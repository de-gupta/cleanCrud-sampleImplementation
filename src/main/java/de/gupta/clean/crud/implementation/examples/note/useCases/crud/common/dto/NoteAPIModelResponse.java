package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto;

public record NoteAPIModelResponse(
		Long id,
		String note
)
{
	public static NoteAPIModelResponse of(
			final long id,
			final String note)
	{
		return new NoteAPIModelResponse(
				id, note);
	}
}