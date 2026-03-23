package de.gupta.clean.crud.implementation.examples.task.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.ConstraintResult;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import org.springframework.stereotype.Component;

@Component
final class TaskExistingModelsConstraintService implements ExistingModelsConstraintService<TaskDomainModel>
{
	@Override
	public ConstraintResult mayThisResourceBeAdded(final TaskDomainModel taskDomainModel)
	{
		// TODO
		return ConstraintResult.satisfied();
	}

	@Override
	public ConstraintResult mayThisResourceBeChangedTo(final TaskDomainModel originalModel,
													   final TaskDomainModel newModel)
	{
		// TODO
		return ConstraintResult.satisfied();
	}
}