package de.gupta.clean.crud.implementation.examples.tag.domain.service.security;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import org.springframework.stereotype.Component;

@Component
final class TagDomainSecurityPolicy implements DomainSecurityPolicy<TagDomainModel>
{
	@Override
	public boolean isAccessAllowed(final TagDomainModel domainModel)
	{
		// TODO from Template: replace this permissive default with real domain visibility rules.
		// Example: return currentUserCanSee(domainModel);
		return true;
	}
}