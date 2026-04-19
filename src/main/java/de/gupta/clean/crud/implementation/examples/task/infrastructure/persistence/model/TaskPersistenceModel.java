package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TaskPersistenceModel extends BasePersistenceModel<UUID>, TaskModel
{
	void setTitle(String title);

	void setDescription(String description);

	Optional<Long> versionId();

	void setVersionId(Long versionId);

	Collection<Long> noteIds();

	void setNoteIds(Collection<Long> noteIds);

	interface TaskPersistenceModelBuilder
			extends TaskModelBuilder<TaskPersistenceModel, TaskPersistenceModelBuilder>,
			ModelBuilder<TaskPersistenceModel>
	{
		TaskPersistenceModelBuilder withVersionId(Optional<Long> versionId);

		TaskPersistenceModelBuilder withNoteIds(Collection<Long> noteIds);
	}
}
