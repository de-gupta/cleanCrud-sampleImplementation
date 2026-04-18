package de.gupta.clean.crud.implementation.examples.note.useCases.crud.save.api.application;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.AbstractSaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.SaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteSaveApplicationController extends
		AbstractSaveApplicationController<NoteAPIModelCreate, NoteAPIModelResponse>
		implements SaveApplicationController<NoteAPIModelCreate,
		NoteAPIModelResponse>
{
	NoteSaveApplicationController(final SaveServiceFacade<NoteAPIModelCreate, NoteAPIModelResponse> service)
	{
		super(service);
	}
}