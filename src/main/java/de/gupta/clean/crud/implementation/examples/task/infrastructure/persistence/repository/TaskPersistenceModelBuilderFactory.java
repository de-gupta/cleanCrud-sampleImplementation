package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskPersistenceModelBuilderFactory
		implements ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder>
{
	@Override
	public TaskPersistenceModel.TaskPersistenceModelBuilder builder()
	{
		return TaskPersistenceModelImpl.builder();
	}
}