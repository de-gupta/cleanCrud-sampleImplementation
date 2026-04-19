package de.gupta.clean.crud.implementation.examples.version.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import org.springframework.stereotype.Component;

@Component
final class VersionChangePolicy implements ChangePolicy<VersionDomainModel>
{
	@Override
	public void validateChangeAttempt(final VersionDomainModel originalModel,
	                                  final VersionDomainModel updatedModel)
	{
		// TODO from Template: validate whether the change from originalModel to updatedModel is allowed.
		// Example: prevent changes to immutable business fields or state transitions.
	}
}