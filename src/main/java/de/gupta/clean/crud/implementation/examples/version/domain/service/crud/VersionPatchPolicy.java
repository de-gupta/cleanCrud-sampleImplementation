package de.gupta.clean.crud.implementation.examples.version.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractPatchPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import org.springframework.stereotype.Component;

@Component
final class VersionPatchPolicy extends AbstractPatchPolicy<VersionDomainModel>
		implements PatchPolicy<VersionDomainModel>
{
	VersionPatchPolicy(
			final ChangePolicy<VersionDomainModel> changePolicy,
			final DomainConstraintService<VersionDomainModel> domainConstraintService)
	{
		super(changePolicy, domainConstraintService);
	}
}