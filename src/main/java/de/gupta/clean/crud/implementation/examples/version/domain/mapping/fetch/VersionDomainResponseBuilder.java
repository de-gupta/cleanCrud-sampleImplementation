package de.gupta.clean.crud.implementation.examples.version.domain.mapping.fetch;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import org.springframework.stereotype.Component;

@Component
final class VersionDomainResponseBuilder implements
		DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse>
{
	@Override
	public VersionDomainModelResponse toResponse(final VersionDomainModel versionDomainModel)
	{
		return VersionDomainModelResponse.fromDomainModel(versionDomainModel);
	}
}