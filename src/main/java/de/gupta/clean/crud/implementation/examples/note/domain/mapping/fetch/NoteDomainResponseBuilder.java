package de.gupta.clean.crud.implementation.examples.note.domain.mapping.fetch;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import org.springframework.stereotype.Component;

@Component
final class NoteDomainResponseBuilder implements
		DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse>
{
	@Override
	public NoteDomainModelResponse toResponse(final NoteDomainModel noteDomainModel)
	{
		return NoteDomainModelResponse.fromDomainModel(noteDomainModel);
	}
}