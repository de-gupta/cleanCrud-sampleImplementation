package de.gupta.clean.crud.implementation.examples.note.useCases.crud.update.api.application;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.AbstractUpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteUpdateApplicationController extends
		AbstractUpdateApplicationController<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long>
		implements UpdateApplicationController<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long>
{
	NoteUpdateApplicationController(
			final UpdateServiceFacade<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long> service)
	{
		super(service);
	}
}