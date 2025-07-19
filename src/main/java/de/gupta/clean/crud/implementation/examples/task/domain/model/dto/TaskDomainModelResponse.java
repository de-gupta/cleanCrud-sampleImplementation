package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;


import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;

import java.util.Optional;

public record TaskDomainModelResponse(
		String title,
		Optional<String> description
)
{
	public static TaskDomainModelResponse fromDomainModel(final TaskDomainModel taskDomainModel)
	{
		return new TaskDomainModelResponse(taskDomainModel.title(), taskDomainModel.description());
	}
}