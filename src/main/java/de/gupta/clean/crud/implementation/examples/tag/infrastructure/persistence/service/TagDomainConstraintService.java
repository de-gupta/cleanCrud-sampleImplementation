package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.AbstractDomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TagDomainConstraintService
		extends AbstractDomainConstraintService<TagDomainModel>
		implements DomainConstraintService<TagDomainModel>
{
	TagDomainConstraintService(
			final DuplicateDefinition<TagDomainModel> duplicateDefinition,
			final DuplicateInsertionMessage<TagDomainModel> duplicateInsertionMessage,
			final ExistingModelsConstraintService<TagDomainModel> existingModelsConstraintService,
			final Supplier<Collection<TagDomainModel>> existingModelsSupplier)
	{
		super(duplicateDefinition, duplicateInsertionMessage, existingModelsConstraintService, existingModelsSupplier);
	}
}