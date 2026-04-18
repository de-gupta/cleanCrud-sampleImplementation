package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.UUID;

public interface NotePersistenceModel extends
		BasePersistenceModel<UUID>, NoteModel
{
	void setNote(String note);

	interface NotePersistenceModelBuilder
			extends NoteModel.NoteModelBuilder<NotePersistenceModel, NotePersistenceModelBuilder>,
			ModelBuilder<NotePersistenceModel>
	{
	}
}