package de.gupta.clean.crud.implementation.examples.tag.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import org.springframework.stereotype.Component;

@Component
final class TagInsertionPolicy extends AbstractInsertionPolicy<TagDomainModel>
		implements InsertionPolicy<TagDomainModel>
{
	TagInsertionPolicy(
			final DomainConstraintService<TagDomainModel> domainConstraintService)
	{
		super(domainConstraintService);
	}
}