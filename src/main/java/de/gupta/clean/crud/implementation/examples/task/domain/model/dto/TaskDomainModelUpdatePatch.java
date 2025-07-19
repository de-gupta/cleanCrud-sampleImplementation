package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import java.util.Optional;

public record TaskDomainModelUpdatePatch(
		Optional<String> title,
		Optional<String> description
)
{
	public static TaskDomainModelUpdatePatch of(final Optional<String> title, final Optional<String> description)
	{
		return new TaskDomainModelUpdatePatch(title, description);
	}
}