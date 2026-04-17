package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.ConstraintResult;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionExistingModelsConstraintService
		implements ExistingModelsConstraintService<TaskVersionDomainModel>
{
	@Override
	public ConstraintResult mayThisResourceBeAdded(final TaskVersionDomainModel model)
	{
		// TODO from Template: add additional insertion-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}

	@Override
	public ConstraintResult mayThisResourceBeChangedTo(
			final TaskVersionDomainModel originalModel,
			final TaskVersionDomainModel newModel)
	{
		// TODO from Template: add additional update-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}
}