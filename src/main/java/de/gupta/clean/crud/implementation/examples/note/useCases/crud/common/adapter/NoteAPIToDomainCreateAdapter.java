package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import org.springframework.stereotype.Component;


@Component
final class NoteAPIToDomainCreateAdapter implements APIToDomainCreateAdapter<NoteAPIModelCreate, NoteDomainModelCreate>
{

	@Override
	public NoteDomainModelCreate mapToDomainModelCreate(final NoteAPIModelCreate apiModel)
	{
		return new NoteDomainModelCreate(
				apiModel.note()
		);
	}

	NoteAPIToDomainCreateAdapter()
	{
	}
}