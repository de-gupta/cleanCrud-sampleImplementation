package de.gupta.clean.crud.implementation.examples.taskversion.domain.mapping.fetch;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDomainResponseBuilder implements
		DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse>
{
	@Override
	public TaskVersionDomainModelResponse toResponse(final TaskVersionDomainModel taskVersionDomainModel)
	{
		return TaskVersionDomainModelResponse.fromDomainModel(taskVersionDomainModel);
	}
}