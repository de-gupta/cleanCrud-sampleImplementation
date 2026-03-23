package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskInsertionPolicy extends AbstractInsertionPolicy<TaskDomainModel>
		implements InsertionPolicy<TaskDomainModel>
{
	TaskInsertionPolicy(
			final DomainConstraintService<TaskDomainModel> domainConstraintService)
	{
		super(domainConstraintService);
	}
}