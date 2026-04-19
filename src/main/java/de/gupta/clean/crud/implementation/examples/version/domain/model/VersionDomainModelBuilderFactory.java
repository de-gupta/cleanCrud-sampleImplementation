package de.gupta.clean.crud.implementation.examples.version.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class VersionDomainModelBuilderFactory implements
		ModelBuilderFactory<VersionDomainModel, VersionDomainModel.VersionDomainModelBuilder>
{
	@Override
	public VersionDomainModel.VersionDomainModelBuilder builder()
	{
		return VersionDomainModelImpl.builder();
	}
}