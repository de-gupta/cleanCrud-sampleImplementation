package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import java.util.Optional;

public record TaskAPIModelResponse(
		Long id,
		String title,
		Optional<String> description
)
{
	public static TaskAPIModelResponse of(final long id, final String title, final Optional<String> description)
	{
		return new TaskAPIModelResponse(id, title, description);
	}
}