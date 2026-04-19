package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.UUID;

public interface VersionPersistenceModel extends
		BasePersistenceModel<UUID>, VersionModel
{
	void setVersion(long version);

	interface VersionPersistenceModelBuilder
			extends
			VersionModel.VersionModelBuilder<VersionPersistenceModel, VersionPersistenceModelBuilder>,
			ModelBuilder<VersionPersistenceModel>
	{
	}
}