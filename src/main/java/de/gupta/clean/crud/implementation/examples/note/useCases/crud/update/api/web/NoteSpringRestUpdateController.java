package de.gupta.clean.crud.implementation.examples.note.useCases.crud.update.api.web;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.security.NoteEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.AbstractSpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.SpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Note Update", description = "Update Operations pertaining to Note")
@RestController
@RequestMapping("/note/update")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = NoteEndpointSecurityPolicy.class)
class NoteSpringRestUpdateController extends
		AbstractSpringRestUpdateController<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long>
		implements SpringRestUpdateController<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long>
{
	NoteSpringRestUpdateController(
			final UpdateServiceFacade<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long> service)
	{
		super(service);
	}
}