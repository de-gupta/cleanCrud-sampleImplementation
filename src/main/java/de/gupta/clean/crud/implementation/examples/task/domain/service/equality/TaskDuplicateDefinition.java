package de.gupta.clean.crud.implementation.examples.task.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.KeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class TaskDuplicateDefinition implements KeyBasedDuplicateDefinition<TaskDomainModel, String>
{
	@Override
	public String duplicateKeyOf(final TaskDomainModel model)
	{
		return model.title();
	}
}