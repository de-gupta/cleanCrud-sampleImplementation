package de.gupta.clean.crud.implementation.examples.taskversion.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDomainModelBuilderFactory implements
		ModelBuilderFactory<TaskVersionDomainModel, TaskVersionDomainModel.TaskVersionDomainModelBuilder>
{
	@Override
	public TaskVersionDomainModel.TaskVersionDomainModelBuilder builder()
	{
		return TaskVersionDomainModelImpl.builder();
	}
}