package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class VersionPersistenceModelBuilderFactory
		implements
		ModelBuilderFactory<VersionPersistenceModel, VersionPersistenceModel.VersionPersistenceModelBuilder>
{
	@Override
	public VersionPersistenceModel.VersionPersistenceModelBuilder builder()
	{
		return VersionPersistenceModelImpl.builder();
	}
}