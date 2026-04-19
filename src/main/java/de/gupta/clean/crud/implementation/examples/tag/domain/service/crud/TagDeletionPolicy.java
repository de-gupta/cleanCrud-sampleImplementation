package de.gupta.clean.crud.implementation.examples.tag.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TagDeletionPolicy implements DeletionPolicy<TagDomainModel>
{
	@Override
	public void validateDeletion(final TagDomainModel tag)
	{
		// TODO from Template: add custom deletion guards here.
		// Example:
		// if (tag.someProperty().contains("important"))
		// {
		//     throw ResourceCannotBeDeletedException.withMessage("An important item cannot be deleted");
		// }
	}
}