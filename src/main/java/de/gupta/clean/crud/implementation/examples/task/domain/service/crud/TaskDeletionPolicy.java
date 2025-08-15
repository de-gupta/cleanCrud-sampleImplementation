package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.model.exceptions.resource.ResourceCannotBeDeletedException;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskDeletionPolicy implements DeletionPolicy<TaskDomainModel>
{
	@Override
	public void validateDeletion(final TaskDomainModel task)
	{
		if (task.title().contains("important"))
		{
			throw ResourceCannotBeDeletedException.withMessage("An important task cannot be deleted");
		}
	}
}