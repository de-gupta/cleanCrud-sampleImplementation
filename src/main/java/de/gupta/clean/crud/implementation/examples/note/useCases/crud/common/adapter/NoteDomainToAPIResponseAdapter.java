package de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.stereotype.Component;


@Component
final class NoteDomainToAPIResponseAdapter
		implements DomainToAPIResponseAdapter<NoteAPIModelResponse,
		Long, NoteDomainModelResponse>
{
	private final APIDomainIDAdapter<Long, Long> idAdapter;

	@Override
	public NoteAPIModelResponse mapToAPIModelResponse(
			final IdentifiedModel<Long,
					NoteDomainModelResponse> domainModel)
	{
		return NoteAPIModelResponse.of(
				idAdapter.mapToAPIModelID(domainModel.id()),
				domainModel.model().note()
		);
	}

	NoteDomainToAPIResponseAdapter(
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		this.idAdapter = idAdapter;
	}
}