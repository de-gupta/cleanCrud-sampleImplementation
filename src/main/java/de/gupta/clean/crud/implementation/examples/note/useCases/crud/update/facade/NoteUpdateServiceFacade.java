package de.gupta.clean.crud.implementation.examples.note.useCases.crud.update.facade;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.facade.AbstractUpdateServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class NoteUpdateServiceFacade extends
		AbstractUpdateServiceFacade<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long,
				NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse, Long>
		implements UpdateServiceFacade<NoteAPIModelCreate, NoteAPIModelUpdatePatch, NoteAPIModelResponse, Long>
{
	NoteUpdateServiceFacade(
			final UpdateService<NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<NoteAPIModelCreate,
					NoteDomainModelCreate> createAdapter,
			final APIToDomainUpdateAdapter<NoteAPIModelUpdatePatch, NoteDomainModelUpdatePatch> updateAdapter,
			final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long,
					NoteDomainModelResponse> responseAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, createAdapter, updateAdapter, responseAdapter, idAdapter);
	}
}