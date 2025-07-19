package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractPatchPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskPatchPolicy extends AbstractPatchPolicy<TaskDomainModel> implements PatchPolicy<TaskDomainModel>
{
	TaskPatchPolicy(final ChangePolicy<TaskDomainModel> changePolicy,
					final InsertionPolicy<TaskDomainModel> insertionPolicy)
	{
		super(changePolicy, insertionPolicy);
	}
}