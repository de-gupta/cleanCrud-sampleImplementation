package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;


@Component
final class NoteAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<NoteAPIModelUpdatePatch, NoteDomainModelUpdatePatch>
{

	@Override
	public NoteDomainModelUpdatePatch mapToDomainModelUpdatePatch(final NoteAPIModelUpdatePatch apiModel)
	{
		return new NoteDomainModelUpdatePatch(
				apiModel.note()
		);
	}

	NoteAPIToDomainUpdateAdapter()
	{
	}
}