package de.gupta.clean.crud.implementation.examples.note.useCases.crud.fetch.api.web;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.security.NoteEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.AbstractSpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.SpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Note Fetch", description = "Fetch Operations pertaining to Note")
@RestController
@RequestMapping("/note/fetch")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = NoteEndpointSecurityPolicy.class)
class NoteSpringRestFetchController extends
		AbstractSpringRestFetchController<NoteAPIModelResponse, Long>
		implements SpringRestFetchController<NoteAPIModelResponse, Long>
{
	NoteSpringRestFetchController(final FetchServiceFacade<NoteAPIModelResponse, Long> service)
	{
		super(service);
	}
}