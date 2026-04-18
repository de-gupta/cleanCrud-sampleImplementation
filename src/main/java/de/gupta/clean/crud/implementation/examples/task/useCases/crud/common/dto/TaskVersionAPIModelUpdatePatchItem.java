package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;

import java.util.Optional;

public record TaskVersionAPIModelUpdatePatchItem(
		Optional<Long> id,
		VersionAPIModelUpdatePatch patch
)
{
	public static TaskVersionAPIModelUpdatePatchItem of(
			final Optional<Long> id,
			final VersionAPIModelUpdatePatch patch)
	{
		return new TaskVersionAPIModelUpdatePatchItem(id, patch);
	}
}
