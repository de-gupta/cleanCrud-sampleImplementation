package de.gupta.clean.crud.implementation.examples.note.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import org.springframework.stereotype.Component;

@Component
final class NoteChangePolicy implements ChangePolicy<NoteDomainModel>
{
	@Override
	public void validateChangeAttempt(final NoteDomainModel originalModel, final NoteDomainModel updatedModel)
	{
		// TODO from Template: validate whether the change from originalModel to updatedModel is allowed.
		// Example: prevent changes to immutable business fields or state transitions.
	}
}
