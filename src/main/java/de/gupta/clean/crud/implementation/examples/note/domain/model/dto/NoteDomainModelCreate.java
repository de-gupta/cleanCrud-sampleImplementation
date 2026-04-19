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

	public static NoteDomainModelCreate fromUpdatePatch(final NoteDomainModelUpdatePatch patch)
	{
		return NoteDomainModelCreate.of(patch.note().orElseThrow());
	}
}