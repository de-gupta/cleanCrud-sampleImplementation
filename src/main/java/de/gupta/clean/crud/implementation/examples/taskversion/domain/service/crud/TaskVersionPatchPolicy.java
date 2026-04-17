package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractPatchPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionPatchPolicy extends AbstractPatchPolicy<TaskVersionDomainModel>
		implements PatchPolicy<TaskVersionDomainModel>
{
	TaskVersionPatchPolicy(
			final ChangePolicy<TaskVersionDomainModel> changePolicy,
			final DomainConstraintService<TaskVersionDomainModel> domainConstraintService)
	{
		super(changePolicy, domainConstraintService);
	}
}