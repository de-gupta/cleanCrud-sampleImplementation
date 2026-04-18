package de.gupta.clean.crud.implementation.examples.note.domain.model.dto;

public record NoteDomainModelCreate(
		String note
)
{
	public static NoteDomainModelCreate of(
			final String note
	)
	{
		return new NoteDomainModelCreate(note);
	}
}