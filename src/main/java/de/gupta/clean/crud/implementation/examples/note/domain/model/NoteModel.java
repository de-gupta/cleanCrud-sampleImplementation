package de.gupta.clean.crud.implementation.examples.note.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.domain.model.validation.Validatable;

public interface NoteModel extends Validatable
{
	String note();

	@Override
	default void validate()
	{
	}

	interface NoteModelBuilder<M extends NoteModel, B extends NoteModelBuilder<M, B>> extends ModelBuilder<M>
	{
		B withNote(final String note);
	}
}