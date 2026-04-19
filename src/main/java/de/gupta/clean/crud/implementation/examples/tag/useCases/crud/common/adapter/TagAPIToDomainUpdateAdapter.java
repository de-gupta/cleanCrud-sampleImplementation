package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;


@Component
final class TagAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<TagAPIModelUpdatePatch, TagDomainModelUpdatePatch>
{

	@Override
	public TagDomainModelUpdatePatch mapToDomainModelUpdatePatch(final TagAPIModelUpdatePatch apiModel)
	{
		return new TagDomainModelUpdatePatch(
				apiModel.name()
		);
	}

	TagAPIToDomainUpdateAdapter()
	{
	}
}