package de.gupta.clean.crud.implementation.examples.tag.domain.mapping.fetch;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import org.springframework.stereotype.Component;

@Component
final class TagDomainResponseBuilder implements
		DomainResponseBuilder<TagDomainModel, TagDomainModelResponse>
{
	@Override
	public TagDomainModelResponse toResponse(final TagDomainModel tagDomainModel)
	{
		return TagDomainModelResponse.fromDomainModel(tagDomainModel);
	}
}