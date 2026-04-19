package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.AbstractDomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class VersionDomainConstraintService
		extends AbstractDomainConstraintService<VersionDomainModel>
		implements DomainConstraintService<VersionDomainModel>
{
	VersionDomainConstraintService(
			final DuplicateDefinition<VersionDomainModel> duplicateDefinition,
			final DuplicateInsertionMessage<VersionDomainModel> duplicateInsertionMessage,
			final ExistingModelsConstraintService<VersionDomainModel> existingModelsConstraintService,
			final Supplier<Collection<VersionDomainModel>> existingModelsSupplier)
	{
		super(duplicateDefinition, duplicateInsertionMessage, existingModelsConstraintService, existingModelsSupplier);
	}
}