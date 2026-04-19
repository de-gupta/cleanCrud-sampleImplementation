package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.stereotype.Component;


@Component
final class TagDomainToAPIResponseAdapter
		implements DomainToAPIResponseAdapter<TagAPIModelResponse,
		Long, TagDomainModelResponse>
{
	private final APIDomainIDAdapter<Long, Long> idAdapter;

	@Override
	public TagAPIModelResponse mapToAPIModelResponse(
			final IdentifiedModel<Long,
					TagDomainModelResponse> domainModel)
	{
		return TagAPIModelResponse.of(
				idAdapter.mapToAPIModelID(domainModel.id()),
				domainModel.model().name()
		);
	}

	TagDomainToAPIResponseAdapter(
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		this.idAdapter = idAdapter;
	}
}