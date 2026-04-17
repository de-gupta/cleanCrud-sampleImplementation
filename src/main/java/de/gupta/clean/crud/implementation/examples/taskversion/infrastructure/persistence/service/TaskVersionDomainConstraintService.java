package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.AbstractDomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TaskVersionDomainConstraintService
		extends AbstractDomainConstraintService<TaskVersionDomainModel>
		implements DomainConstraintService<TaskVersionDomainModel>
{
	TaskVersionDomainConstraintService(
			final DuplicateDefinition<TaskVersionDomainModel> duplicateDefinition,
			final DuplicateInsertionMessage<TaskVersionDomainModel> duplicateInsertionMessage,
			final ExistingModelsConstraintService<TaskVersionDomainModel> existingModelsConstraintService,
			final Supplier<Collection<TaskVersionDomainModel>> existingModelsSupplier)
	{
		super(duplicateDefinition, duplicateInsertionMessage, existingModelsConstraintService, existingModelsSupplier);
	}
}