package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDeletionPolicy implements DeletionPolicy<TaskVersionDomainModel>
{
	@Override
	public void validateDeletion(final TaskVersionDomainModel taskVersion)
	{
		// TODO from Template: add custom deletion guards here.
		// Example:
		// if (taskVersion.someProperty().contains("important"))
		// {
		//     throw ResourceCannotBeDeletedException.withMessage("An important item cannot be deleted");
		// }
	}
}