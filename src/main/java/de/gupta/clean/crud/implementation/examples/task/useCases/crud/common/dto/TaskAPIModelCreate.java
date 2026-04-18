package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import jakarta.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskAPIModelCreate(
		@NotBlank(message = "Title is required")
		String title,
		Optional<String> description,
		Collection<VersionAPIModelCreate> versions
)
{
	public static TaskAPIModelCreate of(final String title, final Optional<String> description,
	                                    final Collection<VersionAPIModelCreate> versions)
	{
		return new TaskAPIModelCreate(title, description, versions);
	}

	public static TaskAPIModelCreate of(final String title, final Optional<String> description)
	{
		return of(title, description, List.of());
	}

	public TaskAPIModelCreate
	{
		description = Optional.ofNullable(description).orElse(Optional.empty());
		versions = versions == null ? List.of() : List.copyOf(versions);
	}
}