package de.gupta.clean.crud.implementation.examples.task.domain.mapping.fetch;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainResponseBuilder implements
		DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse>
{
	@Override
	public TaskDomainModelResponse toResponse(final TaskDomainModel taskDomainModel)
	{
		return new TaskDomainModelResponse(
				taskDomainModel.title(),
				taskDomainModel.description(),
				taskDomainModel.versions());
	}
}
