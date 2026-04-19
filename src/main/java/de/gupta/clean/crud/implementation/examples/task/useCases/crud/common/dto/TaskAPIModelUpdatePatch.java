package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.standard.SatelliteUpdatePatchItem;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskAPIModelUpdatePatch(
		Optional<String> title,
		Optional<String> description,
		Optional<Collection<SatelliteUpdatePatchItem<Long, VersionAPIModelUpdatePatch>>> versions,
		Collection<Long> removeVersionIds,
		Optional<Collection<SatelliteUpdatePatchItem<Long, NoteAPIModelUpdatePatch>>> notes,
		Collection<Long> removeNoteIds
)
{
	public static TaskAPIModelUpdatePatch of(final Optional<String> title, final Optional<String> description)
	{
		return of(title, description, Optional.empty(), List.of(), Optional.empty(), List.of());
	}

	public static TaskAPIModelUpdatePatch of(final Optional<String> title, final Optional<String> description,
	                                         final Optional<Collection<SatelliteUpdatePatchItem<Long, VersionAPIModelUpdatePatch>>> versions,
	                                         final Collection<Long> removeVersionIds,
	                                         final Optional<Collection<SatelliteUpdatePatchItem<Long, NoteAPIModelUpdatePatch>>> notes,
	                                         final Collection<Long> removeNoteIds)
	{
		return new TaskAPIModelUpdatePatch(title, description, versions, removeVersionIds, notes, removeNoteIds);
	}

	public static TaskAPIModelUpdatePatch of(final Optional<String> title, final Optional<String> description,
	                                         final Optional<Collection<SatelliteUpdatePatchItem<Long, VersionAPIModelUpdatePatch>>> versions,
	                                         final Collection<Long> removeVersionIds)
	{
		return of(title, description, versions, removeVersionIds, Optional.empty(), List.of());
	}

	public TaskAPIModelUpdatePatch
	{
		title = Optional.ofNullable(title).orElse(Optional.empty());
		description = Optional.ofNullable(description).orElse(Optional.empty());
		versions = Optional.ofNullable(versions).orElse(Optional.empty()).map(List::copyOf);
		removeVersionIds = Optional.ofNullable(removeVersionIds).map(List::copyOf).orElse(List.of());
		notes = Optional.ofNullable(notes).orElse(Optional.empty()).map(List::copyOf);
		removeNoteIds = Optional.ofNullable(removeNoteIds).map(List::copyOf).orElse(List.of());
	}
}