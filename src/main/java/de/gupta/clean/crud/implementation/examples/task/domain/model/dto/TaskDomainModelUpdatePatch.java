package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskDomainModelUpdatePatch(
		Optional<String> title,
		Optional<String> description,
		Optional<Collection<TaskVersionDomainModelUpdatePatchItem>> versions,
		Collection<Long> removeVersionIds,
		Optional<Collection<TaskNoteDomainModelUpdatePatchItem>> notes,
		Collection<Long> removeNoteIds
)
{
	public static TaskDomainModelUpdatePatch of(
			final Optional<String> title,
			final Optional<String> description,
			final Optional<Collection<TaskVersionDomainModelUpdatePatchItem>> versions,
			final Collection<Long> removeVersionIds,
			final Optional<Collection<TaskNoteDomainModelUpdatePatchItem>> notes,
			final Collection<Long> removeNoteIds)
	{
		return new TaskDomainModelUpdatePatch(
				title,
				description,
				versions.map(List::copyOf),
				List.copyOf(removeVersionIds),
				notes.map(List::copyOf),
				List.copyOf(removeNoteIds));
	}

	public static TaskDomainModelUpdatePatch of(
			final Optional<String> title,
			final Optional<String> description,
			final Optional<Collection<TaskVersionDomainModelUpdatePatchItem>> versions,
			final Collection<Long> removeVersionIds)
	{
		return of(title, description, versions, removeVersionIds, Optional.empty(), List.of());
	}

	public static TaskDomainModelUpdatePatch of(final Optional<String> title, final Optional<String> description)
	{
		return of(
				title,
				description,
				Optional.empty(),
				List.of(),
				Optional.empty(),
				List.of());
	}
}
