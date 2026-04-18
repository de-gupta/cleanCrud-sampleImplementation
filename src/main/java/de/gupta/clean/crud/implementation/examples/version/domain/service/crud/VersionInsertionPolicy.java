package de.gupta.clean.crud.implementation.examples.version.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import org.springframework.stereotype.Component;

@Component
final class VersionInsertionPolicy extends AbstractInsertionPolicy<VersionDomainModel>
		implements InsertionPolicy<VersionDomainModel>
{
	VersionInsertionPolicy(
			final DomainConstraintService<VersionDomainModel> domainConstraintService)
	{
		super(domainConstraintService);
	}
}