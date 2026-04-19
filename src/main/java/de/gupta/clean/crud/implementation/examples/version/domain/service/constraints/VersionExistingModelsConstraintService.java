package de.gupta.clean.crud.implementation.examples.version.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.ConstraintResult;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import org.springframework.stereotype.Component;

@Component
final class VersionExistingModelsConstraintService
		implements ExistingModelsConstraintService<VersionDomainModel>
{
	@Override
	public ConstraintResult mayThisResourceBeAdded(final VersionDomainModel model)
	{
		// TODO from Template: add additional insertion-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}

	@Override
	public ConstraintResult mayThisResourceBeChangedTo(
			final VersionDomainModel originalModel,
			final VersionDomainModel newModel)
	{
		// TODO from Template: add additional update-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}
}