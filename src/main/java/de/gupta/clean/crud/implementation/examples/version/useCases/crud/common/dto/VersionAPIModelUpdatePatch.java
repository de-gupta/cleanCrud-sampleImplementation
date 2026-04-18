package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto;

import java.util.Optional;

public record VersionAPIModelUpdatePatch(
		Optional<Long> version
)
{
	public static VersionAPIModelUpdatePatch of(final Optional<Long> version)
	{
		return new VersionAPIModelUpdatePatch(version);
	}
}
