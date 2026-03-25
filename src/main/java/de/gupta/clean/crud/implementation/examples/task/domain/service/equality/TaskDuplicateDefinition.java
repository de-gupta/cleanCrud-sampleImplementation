package de.gupta.clean.crud.implementation.examples.task.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.AbstractKeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class TaskDuplicateDefinition extends AbstractKeyBasedDuplicateDefinition<TaskDomainModel, String>
{
	@Override
	public String duplicateKeyOf(final TaskDomainModel model)
	{
		return model.title();
	}
}