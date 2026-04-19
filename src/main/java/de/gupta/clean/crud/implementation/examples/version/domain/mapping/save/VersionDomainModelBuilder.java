package de.gupta.clean.crud.implementation.examples.version.domain.mapping.save;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class VersionDomainModelBuilder
		implements DomainModelBuilder<VersionDomainModelCreate, VersionDomainModel>
{
	private final ModelBuilderFactory<VersionDomainModel, VersionDomainModel.VersionDomainModelBuilder>
			modelBuilderFactory;

	@Override
	public VersionDomainModel toModel(final VersionDomainModelCreate domainModelCreate)
	{
		return modelBuilderFactory.builder()
		                          .withVersion(domainModelCreate.version())
		                          .build();
	}

	VersionDomainModelBuilder(
			final ModelBuilderFactory<VersionDomainModel, VersionDomainModel.VersionDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}