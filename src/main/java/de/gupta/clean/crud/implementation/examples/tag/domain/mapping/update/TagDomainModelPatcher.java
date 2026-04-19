package de.gupta.clean.crud.implementation.examples.tag.domain.mapping.update;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TagDomainModelPatcher implements DomainModelPatcher<TagDomainModel, TagDomainModelUpdatePatch>
{
	private final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory;

	@Override
	public TagDomainModel patchModel(final TagDomainModel originalModel,
	                                 final TagDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
		                          .withName(updatePatch.name().orElse(originalModel.name()))

		                          .build();
	}

	TagDomainModelPatcher(
			final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}