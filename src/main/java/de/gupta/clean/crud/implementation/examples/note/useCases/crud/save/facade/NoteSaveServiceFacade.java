package de.gupta.clean.crud.implementation.examples.note.useCases.crud.save.facade;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.save.facade.AbstractSaveServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteSaveServiceFacade extends
		AbstractSaveServiceFacade<NoteAPIModelCreate, NoteAPIModelResponse,
				NoteDomainModelCreate, NoteDomainModelResponse, Long>
		implements SaveServiceFacade<NoteAPIModelCreate, NoteAPIModelResponse>
{
	NoteSaveServiceFacade(
			final SaveService<NoteDomainModelCreate, NoteDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<NoteAPIModelCreate, NoteDomainModelCreate> createMapper,
			final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> responseMapper)
	{
		super(service, createMapper, responseMapper);
	}
}