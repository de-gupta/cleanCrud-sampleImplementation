package de.gupta.clean.crud.implementation.examples.note.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

@Component
final class NoteDuplicateInsertionMessage implements DuplicateInsertionMessage<NoteDomainModel>
{
	@Override
	public String messageIfModelAlreadyExists(final NoteDomainModel noteDomainModel)
	{
		// TODO from Template: customize this duplicate message for the business key your API should expose.
		return "The note with note `" + noteDomainModel.note() + "` already exists";
	}
}