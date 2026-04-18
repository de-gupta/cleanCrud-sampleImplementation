package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskDomainModelCreate(
		String title,
		Optional<String> description,
		Collection<VersionAPIModelCreate> versions
)
{
	public static TaskDomainModelCreate of(
			final String title,
			final Optional<String> description,
			final Collection<VersionAPIModelCreate> versions)
	{
		return new TaskDomainModelCreate(title, description, List.copyOf(versions));
	}

	public static TaskDomainModelCreate of(final String title, final Optional<String> description)
	{
		return of(title, description, List.of());
	}

	public static TaskDomainModelCreate of(final String title)
	{
		return of(title, Optional.empty(), List.of());
	}

	public static TaskDomainModelCreate of(final String title, final String description)
	{
		return of(title, Optional.of(description), List.of());
	}
}