package de.gupta.clean.crud.implementation.examples.version.domain.model.dto;

public record VersionDomainModelCreate(
		long version
)
{
	public static VersionDomainModelCreate of(
			final long version
	)
	{
		return new VersionDomainModelCreate(version);
	}
}