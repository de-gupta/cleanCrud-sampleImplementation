package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskAPIModelResponse(
		Long id,
		String title,
		Optional<String> description,
		Collection<VersionAPIModelResponse> versions,
		Collection<NoteAPIModelResponse> notes
)
{
	public static TaskAPIModelResponse of(
			final long id,
			final String title,
			final Optional<String> description,
			final Collection<VersionAPIModelResponse> versions,
			final Collection<NoteAPIModelResponse> notes)
	{
		return new TaskAPIModelResponse(id, title, description, versions, notes);
	}

	public static TaskAPIModelResponse of(
			final long id,
			final String title,
			final Optional<String> description,
			final Collection<VersionAPIModelResponse> versions)
	{
		return of(id, title, description, versions, List.of());
	}

	public TaskAPIModelResponse
	{
		versions = List.copyOf(versions);
		notes = List.copyOf(notes);
	}
}
