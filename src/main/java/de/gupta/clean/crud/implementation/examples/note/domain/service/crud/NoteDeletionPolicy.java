package de.gupta.clean.crud.implementation.examples.note.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
final class NoteDeletionPolicy implements DeletionPolicy<NoteDomainModel>
{
	@Override
	public void validateDeletion(final NoteDomainModel note)
	{
		// TODO from Template: add custom deletion guards here.
		// Example:
		// if (note.someProperty().contains("important"))
		// {
		//     throw ResourceCannotBeDeletedException.withMessage("An important item cannot be deleted");
		// }
	}
}