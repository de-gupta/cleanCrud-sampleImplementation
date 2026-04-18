package de.gupta.clean.crud.implementation.examples.version.domain.model.dto;

import java.util.Optional;

public record VersionDomainModelUpdatePatch(
		Optional<Long> version
)
{
	public static VersionDomainModelUpdatePatch of(
			final Optional<Long> version
	)
	{
		return new VersionDomainModelUpdatePatch(version);
	}
}