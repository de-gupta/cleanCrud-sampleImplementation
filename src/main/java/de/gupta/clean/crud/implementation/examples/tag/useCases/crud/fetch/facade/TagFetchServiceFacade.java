package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.fetch.facade;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.AbstractFetchServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagFetchServiceFacade extends
		AbstractFetchServiceFacade<TagAPIModelResponse, Long, TagDomainModel, TagDomainModelResponse, Long>
		implements FetchServiceFacade<TagAPIModelResponse,
		Long>
{
	TagFetchServiceFacade(final FetchService<TagDomainModel, Long> service,
	                      final DomainToAPIResponseAdapter<TagAPIModelResponse, Long, TagDomainModelResponse> responseMapper,
	                      final DomainResponseBuilder<TagDomainModel, TagDomainModelResponse> responseBuilder,
	                      final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, responseMapper, responseBuilder, idAdapter);
	}
}