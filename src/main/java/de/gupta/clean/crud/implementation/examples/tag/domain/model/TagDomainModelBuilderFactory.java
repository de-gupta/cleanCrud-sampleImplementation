package de.gupta.clean.crud.implementation.examples.tag.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TagDomainModelBuilderFactory implements
		ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder>
{
	@Override
	public TagDomainModel.TagDomainModelBuilder builder()
	{
		return TagDomainModelImpl.builder();
	}
}