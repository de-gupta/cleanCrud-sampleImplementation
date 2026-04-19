package de.gupta.clean.crud.implementation.examples.note.domain.service.security;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import org.springframework.stereotype.Component;

@Component
final class NoteDomainSecurityPolicy implements DomainSecurityPolicy<NoteDomainModel>
{
	@Override
	public boolean isAccessAllowed(final NoteDomainModel domainModel)
	{
		// TODO from Template: replace this permissive default with real domain visibility rules.
		// Example: return currentUserCanSee(domainModel);
		return true;
	}
}