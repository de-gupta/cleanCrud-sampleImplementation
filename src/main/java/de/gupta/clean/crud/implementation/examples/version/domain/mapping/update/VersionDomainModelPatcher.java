package de.gupta.clean.crud.implementation.examples.version.domain.mapping.update;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class VersionDomainModelPatcher
		implements DomainModelPatcher<VersionDomainModel, VersionDomainModelUpdatePatch>
{
	private final ModelBuilderFactory<VersionDomainModel, VersionDomainModel.VersionDomainModelBuilder>
			modelBuilderFactory;

	@Override
	public VersionDomainModel patchModel(final VersionDomainModel originalModel,
	                                     final VersionDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
		                          .withVersion(updatePatch.version().orElse(originalModel.version()))

		                          .build();
	}

	VersionDomainModelPatcher(
			final ModelBuilderFactory<VersionDomainModel, VersionDomainModel.VersionDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}