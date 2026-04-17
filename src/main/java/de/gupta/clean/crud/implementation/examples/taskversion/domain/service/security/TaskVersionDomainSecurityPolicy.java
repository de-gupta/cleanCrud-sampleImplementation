package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.security;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDomainSecurityPolicy implements DomainSecurityPolicy<TaskVersionDomainModel>
{
	@Override
	public boolean isAccessAllowed(final TaskVersionDomainModel domainModel)
	{
		// TODO from Template: replace this permissive default with real domain visibility rules.
		// Example: return currentUserCanSee(domainModel);
		return true;
	}
}