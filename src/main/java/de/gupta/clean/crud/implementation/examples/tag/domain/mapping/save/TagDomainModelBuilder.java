package de.gupta.clean.crud.implementation.examples.tag.domain.mapping.save;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TagDomainModelBuilder implements DomainModelBuilder<TagDomainModelCreate, TagDomainModel>
{
	private final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory;

	@Override
	public TagDomainModel toModel(final TagDomainModelCreate domainModelCreate)
	{
		return modelBuilderFactory.builder()
		                          .withName(domainModelCreate.name())
		                          .build();
	}

	TagDomainModelBuilder(
			final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}