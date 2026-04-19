package de.gupta.clean.crud.implementation.examples.note.useCases.crud.fetch.facade;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.AbstractFetchServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteFetchServiceFacade extends
		AbstractFetchServiceFacade<NoteAPIModelResponse, Long, NoteDomainModel, NoteDomainModelResponse, Long>
		implements FetchServiceFacade<NoteAPIModelResponse,
		Long>
{
	NoteFetchServiceFacade(final FetchService<NoteDomainModel, Long> service,
	                       final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> responseMapper,
	                       final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> responseBuilder,
	                       final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, responseMapper, responseBuilder, idAdapter);
	}
}