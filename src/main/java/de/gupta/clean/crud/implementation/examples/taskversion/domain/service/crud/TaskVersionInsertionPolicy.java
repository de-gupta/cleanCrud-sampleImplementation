package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionInsertionPolicy extends AbstractInsertionPolicy<TaskVersionDomainModel>
		implements InsertionPolicy<TaskVersionDomainModel>
{
	TaskVersionInsertionPolicy(
			final DomainConstraintService<TaskVersionDomainModel> domainConstraintService)
	{
		super(domainConstraintService);
	}
}