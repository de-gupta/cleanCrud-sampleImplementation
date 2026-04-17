package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionChangePolicy implements ChangePolicy<TaskVersionDomainModel>
{
	@Override
	public void validateChangeAttempt(final TaskVersionDomainModel originalModel,
	                                  final TaskVersionDomainModel updatedModel)
	{
		// TODO from Template: validate whether the change from originalModel to updatedModel is allowed.
		// Example: prevent changes to immutable business fields or state transitions.
	}
}