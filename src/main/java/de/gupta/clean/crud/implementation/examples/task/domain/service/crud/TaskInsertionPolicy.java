package de.gupta.clean.crud.implementation.examples.task.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import de.gupta.clean.crud.template.domain.service.existence.ResourceExistenceDetectionService;
import org.springframework.stereotype.Component;

@Component
final class TaskInsertionPolicy extends AbstractInsertionPolicy<TaskDomainModel>
		implements InsertionPolicy<TaskDomainModel>
{
	TaskInsertionPolicy(
			final ResourceExistenceDetectionService<TaskDomainModel> resourceExistenceDetectionService,
			final DuplicateInsertionMessage<TaskDomainModel> duplicateInsertionMessage)
	{
		super(resourceExistenceDetectionService, duplicateInsertionMessage);
	}
}