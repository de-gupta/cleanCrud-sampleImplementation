package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.AbstractDomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class NoteDomainConstraintService
		extends AbstractDomainConstraintService<NoteDomainModel>
		implements DomainConstraintService<NoteDomainModel>
{
	NoteDomainConstraintService(
			final DuplicateDefinition<NoteDomainModel> duplicateDefinition,
			final DuplicateInsertionMessage<NoteDomainModel> duplicateInsertionMessage,
			final ExistingModelsConstraintService<NoteDomainModel> existingModelsConstraintService,
			final Supplier<Collection<NoteDomainModel>> existingModelsSupplier)
	{
		super(duplicateDefinition, duplicateInsertionMessage, existingModelsConstraintService, existingModelsSupplier);
	}
}