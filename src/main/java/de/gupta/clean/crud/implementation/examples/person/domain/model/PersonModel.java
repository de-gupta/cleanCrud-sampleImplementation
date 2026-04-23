package de.gupta.clean.crud.implementation.examples.person.domain.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface PersonModel<O, V, N>
{
	String firstName();

	O organisation();

	V version();

	Optional<V> lastKnownVersion();

	Collection<N> notes();

	Optional<String> title();

	LocalDate birthDate();
}