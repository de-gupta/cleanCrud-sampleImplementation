package de.gupta.clean.crud.implementation.examples.tag.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;

public record TagDomainModelResponse(
		String name
)
{
	public static TagDomainModelResponse fromDomainModel(final TagDomainModel tagDomainModel)
	{
		return new TagDomainModelResponse(
				tagDomainModel.name());
	}
}