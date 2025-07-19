package de.gupta.clean.crud.implementation.examples.person.domain.model;

import java.util.Optional;

public interface PersonModel<U, V>
{
	U user();

	Optional<V> something();

	Optional<String> title();

	String firstName();

	Optional<String> lastName();
}