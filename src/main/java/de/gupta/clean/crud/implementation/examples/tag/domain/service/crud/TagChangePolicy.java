package de.gupta.clean.crud.implementation.examples.tag.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import org.springframework.stereotype.Component;

@Component
final class TagChangePolicy implements ChangePolicy<TagDomainModel>
{
	@Override
	public void validateChangeAttempt(final TagDomainModel originalModel, final TagDomainModel updatedModel)
	{
		// TODO from Template: validate whether the change from originalModel to updatedModel is allowed.
		// Example: prevent changes to immutable business fields or state transitions.
	}
}
