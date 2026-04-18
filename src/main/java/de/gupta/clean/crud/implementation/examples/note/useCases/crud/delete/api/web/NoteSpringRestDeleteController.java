package de.gupta.clean.crud.implementation.examples.note.useCases.crud.delete.api.web;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.security.NoteEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.AbstractSpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.SpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Note Delete", description = "Delete Operations pertaining to Note")
@RestController
@RequestMapping("/note/delete")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = NoteEndpointSecurityPolicy.class)
class NoteSpringRestDeleteController extends AbstractSpringRestDeleteController<Long>
		implements SpringRestDeleteController<Long>
{
	NoteSpringRestDeleteController(
			@Qualifier("noteDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}