package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import org.springframework.stereotype.Component;


@Component
final class TagAPIToDomainCreateAdapter implements APIToDomainCreateAdapter<TagAPIModelCreate, TagDomainModelCreate>
{

	@Override
	public TagDomainModelCreate mapToDomainModelCreate(final TagAPIModelCreate apiModel)
	{
		return new TagDomainModelCreate(
				apiModel.name()
		);
	}

	TagAPIToDomainCreateAdapter()
	{
	}
}