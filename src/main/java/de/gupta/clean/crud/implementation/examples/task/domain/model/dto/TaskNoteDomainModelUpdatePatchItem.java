package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;

import java.util.Optional;

public record TaskNoteDomainModelUpdatePatchItem(
		Optional<Long> id,
		NoteAPIModelUpdatePatch patch
)
{
	public static TaskNoteDomainModelUpdatePatchItem of(
			final Optional<Long> id,
			final NoteAPIModelUpdatePatch patch)
	{
		return new TaskNoteDomainModelUpdatePatchItem(id, patch);
	}
}
