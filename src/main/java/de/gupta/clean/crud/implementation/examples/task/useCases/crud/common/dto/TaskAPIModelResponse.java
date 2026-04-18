package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskAPIModelResponse(
		Long id,
		String title,
		Optional<String> description,
		Collection<TaskVersionAPIModelResponse> versions
)
{
	public static TaskAPIModelResponse of(
			final long id,
			final String title,
			final Optional<String> description,
			final Collection<TaskVersionAPIModelResponse> versions)
	{
		return new TaskAPIModelResponse(id, title, description, versions);
	}

	public TaskAPIModelResponse
	{
		versions = List.copyOf(versions);
	}
}