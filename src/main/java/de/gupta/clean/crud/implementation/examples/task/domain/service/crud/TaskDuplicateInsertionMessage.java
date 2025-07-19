package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

@Component
final class TaskDuplicateInsertionMessage implements DuplicateInsertionMessage<TaskDomainModel>
{
	@Override
	public String messageIfModelAlreadyExists(final TaskDomainModel taskDomainModel)
	{
		return "The task with title `" + taskDomainModel.title() + "` already exists";
	}
}