package de.gupta.clean.crud.implementation.examples.version.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
final class VersionDeletionPolicy implements DeletionPolicy<VersionDomainModel>
{
	@Override
	public void validateDeletion(final VersionDomainModel version)
	{
		// TODO from Template: add custom deletion guards here.
		// Example:
		// if (version.someProperty().contains("important"))
		// {
		//     throw ResourceCannotBeDeletedException.withMessage("An important item cannot be deleted");
		// }
	}
}