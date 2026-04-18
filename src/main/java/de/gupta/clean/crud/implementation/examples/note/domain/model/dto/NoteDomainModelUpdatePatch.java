package de.gupta.clean.crud.implementation.examples.note.domain.model.dto;

import java.util.Optional;

public record NoteDomainModelUpdatePatch(
		Optional<String> note
)
{
	public static NoteDomainModelUpdatePatch of(
			final Optional<String> note
	)
	{
		return new NoteDomainModelUpdatePatch(note);
	}
}