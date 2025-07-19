package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import java.util.Optional;

public record TaskDomainModelCreate(
		String title,
		Optional<String> description
)
{
	public static TaskDomainModelCreate of(final String title, final Optional<String> description)
	{
		return new TaskDomainModelCreate(title, description);
	}

	public static TaskDomainModelCreate of(final String title)
	{
		return new TaskDomainModelCreate(title, Optional.empty());
	}

	public static TaskDomainModelCreate of(final String title, final String description)
	{
		return new TaskDomainModelCreate(title, Optional.of(description));
	}
}