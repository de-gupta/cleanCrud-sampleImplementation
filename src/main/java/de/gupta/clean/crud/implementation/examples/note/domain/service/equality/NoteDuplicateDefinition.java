package de.gupta.clean.crud.implementation.examples.note.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.KeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class NoteDuplicateDefinition
		implements KeyBasedDuplicateDefinition<NoteDomainModel, NoteDuplicateKey>
{
	@Override
	public NoteDuplicateKey duplicateKeyOf(final NoteDomainModel model)
	{
		// TODO from Template: replace this default duplicate key with the business key your API should use.
		// TODO from Template: if key-based duplicate detection does not fit this domain, delete this class and implement DuplicateDefinition directly.
		return new NoteDuplicateKey(
				model.note()
		);
	}
}