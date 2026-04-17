package de.gupta.clean.crud.implementation.examples.taskversion.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.KeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDuplicateDefinition
		implements KeyBasedDuplicateDefinition<TaskVersionDomainModel, TaskVersionDuplicateKey>
{
	@Override
	public TaskVersionDuplicateKey duplicateKeyOf(final TaskVersionDomainModel model)
	{
		// TODO from Template: replace this default duplicate key with the business key your API should use.
		// TODO from Template: if key-based duplicate detection does not fit this domain, delete this class and implement DuplicateDefinition directly.
		return new TaskVersionDuplicateKey(
				model.version()
		);
	}
}