package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;


import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskDomainModelResponse(
		String title,
		Optional<String> description,
		Collection<TaskVersionAPIModelResponse> versions
)
{
	public TaskDomainModelResponse
	{
		versions = List.copyOf(versions);
	}
}
