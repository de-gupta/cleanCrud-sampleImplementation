package de.gupta.clean.crud.implementation.examples.person.domain.model;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface PersonModel<U, V>
{
	U user();

	Optional<V> something();

	Optional<String> title();

	String firstName();

	Optional<String> lastName();

	LocalDate birthDate();

	Collection<NoteAPIModelResponse> notes();

	VersionAPIModelResponse currentVersion();

	Optional<VersionAPIModelResponse> lastKnownVersion();
}