package de.gupta.clean.crud.implementation.examples.note.useCases.crud.delete.api.application;

import de.gupta.clean.crud.template.useCases.crud.delete.api.application.AbstractDeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.application.DeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
final class NoteDeleteApplicationController extends AbstractDeleteApplicationController<Long>
		implements DeleteApplicationController<Long>
{
	NoteDeleteApplicationController(
			@Qualifier("noteDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}