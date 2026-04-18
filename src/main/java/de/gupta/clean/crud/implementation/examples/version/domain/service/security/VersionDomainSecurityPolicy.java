package de.gupta.clean.crud.implementation.examples.version.domain.service.security;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import org.springframework.stereotype.Component;

@Component
final class VersionDomainSecurityPolicy implements DomainSecurityPolicy<VersionDomainModel>
{
	@Override
	public boolean isAccessAllowed(final VersionDomainModel domainModel)
	{
		// TODO from Template: replace this permissive default with real domain visibility rules.
		// Example: return currentUserCanSee(domainModel);
		return true;
	}
}