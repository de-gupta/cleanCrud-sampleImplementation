package de.gupta.clean.crud.implementation.examples.version.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;

public record VersionDomainModelResponse(
		long version
)
{
	public static VersionDomainModelResponse fromDomainModel(final VersionDomainModel versionDomainModel)
	{
		return new VersionDomainModelResponse(
				versionDomainModel.version());
	}
}