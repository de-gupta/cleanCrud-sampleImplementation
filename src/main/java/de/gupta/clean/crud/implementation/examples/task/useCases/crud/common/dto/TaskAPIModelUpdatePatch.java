package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelUpdatePatch;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskAPIModelUpdatePatch(
		Optional<String> title,
		Optional<String> description,
		Optional<Collection<TaskVersionAPIModelUpdatePatch>> versions,
		Collection<Long> removeVersionIds
)
{
	public static TaskAPIModelUpdatePatch of(final Optional<String> title, final Optional<String> description)
	{
		return of(title, description, Optional.empty(), List.of());
	}

	public static TaskAPIModelUpdatePatch of(final Optional<String> title, final Optional<String> description,
	                                         final Optional<Collection<TaskVersionAPIModelUpdatePatch>> versions,
	                                         final Collection<Long> removeVersionIds)
	{
		return new TaskAPIModelUpdatePatch(title, description, versions, removeVersionIds);
	}

	public TaskAPIModelUpdatePatch
	{
		title = Optional.ofNullable(title).orElse(Optional.empty());
		description = Optional.ofNullable(description).orElse(Optional.empty());
		versions = Optional.ofNullable(versions).orElse(Optional.empty()).map(List::copyOf);
		removeVersionIds = removeVersionIds == null ? List.of() : List.copyOf(removeVersionIds);
	}
}