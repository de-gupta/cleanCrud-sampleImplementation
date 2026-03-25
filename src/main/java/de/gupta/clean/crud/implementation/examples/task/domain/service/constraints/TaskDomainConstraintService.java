package de.gupta.clean.crud.implementation.examples.task.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.AbstractDomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TaskDomainConstraintService extends AbstractDomainConstraintService<TaskDomainModel>
		implements DomainConstraintService<TaskDomainModel>
{
	TaskDomainConstraintService(
			final DuplicateDefinition<TaskDomainModel> duplicateDefinition,
			final DuplicateInsertionMessage<TaskDomainModel> duplicateInsertionMessage,
			final ExistingModelsConstraintService<TaskDomainModel> existingModelsConstraintService,
			final Supplier<Collection<TaskDomainModel>> existingModelsSupplier)
	{
		super(duplicateDefinition, duplicateInsertionMessage, existingModelsConstraintService, existingModelsSupplier);
	}
}