package de.gupta.clean.crud.implementation.examples.task.domain.service.security;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainSecurityPolicy implements DomainSecurityPolicy<TaskDomainModel>
{
	@Override
	public boolean isAccessAllowed(final TaskDomainModel taskDomainModel)
	{
		return true;
	}
}