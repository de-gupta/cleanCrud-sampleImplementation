package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainModelBuilderFactory implements
		ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder>
{
	@Override
	public TaskDomainModel.TaskDomainModelBuilder builder()
	{
		return TaskDomainModelImpl.builder();
	}
}