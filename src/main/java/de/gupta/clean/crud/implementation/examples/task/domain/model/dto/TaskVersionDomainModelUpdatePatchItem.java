package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;

import java.util.Optional;

public record TaskVersionDomainModelUpdatePatchItem(
		Optional<Long> id,
		VersionAPIModelUpdatePatch patch
)
{
	public static TaskVersionDomainModelUpdatePatchItem of(
			final Optional<Long> id,
			final VersionAPIModelUpdatePatch patch)
	{
		return new TaskVersionDomainModelUpdatePatchItem(id, patch);
	}
}
