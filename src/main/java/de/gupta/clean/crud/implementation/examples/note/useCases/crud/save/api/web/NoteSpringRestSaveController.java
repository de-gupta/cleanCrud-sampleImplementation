package de.gupta.clean.crud.implementation.examples.note.useCases.crud.save.api.web;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.security.NoteEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.AbstractSpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.SpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Note Save", description = "Save Operations pertaining to Note")
@RestController
@RequestMapping("/note/save")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = NoteEndpointSecurityPolicy.class)
class NoteSpringRestSaveController extends
		AbstractSpringRestSaveController<NoteAPIModelCreate, NoteAPIModelResponse>
		implements SpringRestSaveController<NoteAPIModelCreate,
		NoteAPIModelResponse>
{
	NoteSpringRestSaveController(final SaveServiceFacade<NoteAPIModelCreate, NoteAPIModelResponse> service)
	{
		super(service);
	}
}