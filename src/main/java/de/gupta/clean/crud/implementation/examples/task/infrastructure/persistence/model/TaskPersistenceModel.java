package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.UUID;

public interface TaskPersistenceModel extends BasePersistenceModel<UUID>, TaskModel
{
	void setTitle(String title);

	void setDescription(String description);

	interface TaskPersistenceModelBuilder
			extends TaskModelBuilder<TaskPersistenceModel, TaskPersistenceModelBuilder>,
			ModelBuilder<TaskPersistenceModel>
	{
	}
}