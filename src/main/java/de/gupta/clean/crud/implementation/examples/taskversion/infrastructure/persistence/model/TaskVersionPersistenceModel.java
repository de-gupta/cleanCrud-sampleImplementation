package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.UUID;

public interface TaskVersionPersistenceModel extends
		BasePersistenceModel<UUID>, TaskVersionModel
{
	void setVersion(long version);

	interface TaskVersionPersistenceModelBuilder
			extends
			TaskVersionModel.TaskVersionModelBuilder<TaskVersionPersistenceModel, TaskVersionPersistenceModelBuilder>,
			ModelBuilder<TaskVersionPersistenceModel>
	{
	}
}