package de.gupta.clean.crud.implementation.examples.note.useCases.crud.fetch.api.application;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.AbstractFetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.FetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteFetchApplicationController extends
		AbstractFetchApplicationController<NoteAPIModelResponse, Long>
		implements FetchApplicationController<NoteAPIModelResponse, Long>
{
	NoteFetchApplicationController(final FetchServiceFacade<NoteAPIModelResponse, Long> service)
	{
		super(service);
	}
}