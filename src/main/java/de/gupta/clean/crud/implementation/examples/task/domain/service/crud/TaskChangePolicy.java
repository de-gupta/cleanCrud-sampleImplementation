package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.model.exceptions.resource.ResourceCannotBePatchedException;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskChangePolicy implements ChangePolicy<TaskDomainModel>
{
	@Override
	public void validateChangeAttempt(final TaskDomainModel originalModel, final TaskDomainModel updatedModel)
	{
		if (originalModel.title().contains("important") && !updatedModel.title().contains("important"))
		{
			throw ResourceCannotBePatchedException.withMessage("An important task cannot be made unimportant");
		}
	}
}