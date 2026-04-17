package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionPersistenceModelBuilderFactory
		implements
		ModelBuilderFactory<TaskVersionPersistenceModel, TaskVersionPersistenceModel.TaskVersionPersistenceModelBuilder>
{
	@Override
	public TaskVersionPersistenceModel.TaskVersionPersistenceModelBuilder builder()
	{
		return TaskVersionPersistenceModelImpl.builder();
	}
}