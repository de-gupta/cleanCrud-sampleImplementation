package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;


import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskDomainModelResponse(
		String title,
		Optional<String> description,
		Collection<VersionAPIModelResponse> versions,
		Collection<NoteAPIModelResponse> notes
)
{
	public TaskDomainModelResponse
	{
		versions = List.copyOf(versions);
		notes = List.copyOf(notes);
	}
}
