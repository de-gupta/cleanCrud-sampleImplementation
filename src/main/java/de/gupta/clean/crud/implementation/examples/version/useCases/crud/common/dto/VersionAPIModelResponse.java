package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto;

public record VersionAPIModelResponse(
		Long id,
		long version
)
{
	public static VersionAPIModelResponse of(
			final long id,
			final long version)
	{
		return new VersionAPIModelResponse(
				id, version);
	}
}