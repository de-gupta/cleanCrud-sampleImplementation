package de.gupta.clean.crud.implementation.examples.tag.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractPatchPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import org.springframework.stereotype.Component;

@Component
final class TagPatchPolicy extends AbstractPatchPolicy<TagDomainModel>
		implements PatchPolicy<TagDomainModel>
{
	TagPatchPolicy(
			final ChangePolicy<TagDomainModel> changePolicy,
			final DomainConstraintService<TagDomainModel> domainConstraintService)
	{
		super(changePolicy, domainConstraintService);
	}
}