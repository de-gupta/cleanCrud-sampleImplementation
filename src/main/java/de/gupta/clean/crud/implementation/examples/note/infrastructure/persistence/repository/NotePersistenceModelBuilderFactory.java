package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class NotePersistenceModelBuilderFactory
		implements ModelBuilderFactory<NotePersistenceModel, NotePersistenceModel.NotePersistenceModelBuilder>
{
	@Override
	public NotePersistenceModel.NotePersistenceModelBuilder builder()
	{
		return NotePersistenceModelImpl.builder();
	}
}