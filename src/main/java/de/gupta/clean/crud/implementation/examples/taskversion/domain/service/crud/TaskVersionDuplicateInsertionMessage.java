package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDuplicateInsertionMessage implements DuplicateInsertionMessage<TaskVersionDomainModel>
{
	@Override
	public String messageIfModelAlreadyExists(final TaskVersionDomainModel taskVersionDomainModel)
	{
		// TODO from Template: customize this duplicate message for the business key your API should expose.
		return "The taskversion with version `" + taskVersionDomainModel.version() + "` already exists";
	}
}