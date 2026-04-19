package de.gupta.clean.crud.implementation.examples.tag.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.ConstraintResult;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import org.springframework.stereotype.Component;

@Component
final class TagExistingModelsConstraintService
		implements ExistingModelsConstraintService<TagDomainModel>
{
	@Override
	public ConstraintResult mayThisResourceBeAdded(final TagDomainModel model)
	{
		// TODO from Template: add additional insertion-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}

	@Override
	public ConstraintResult mayThisResourceBeChangedTo(
			final TagDomainModel originalModel,
			final TagDomainModel newModel)
	{
		// TODO from Template: add additional update-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}
}