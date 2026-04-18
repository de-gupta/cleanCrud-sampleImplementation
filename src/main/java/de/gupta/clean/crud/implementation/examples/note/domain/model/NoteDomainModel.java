package de.gupta.clean.crud.implementation.examples.note.domain.model;

import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

public interface NoteDomainModel extends
		BaseDomainModel, NoteModel
{
	interface NoteDomainModelBuilder extends NoteModel.NoteModelBuilder<NoteDomainModel, NoteDomainModelBuilder>
	{
	}
}