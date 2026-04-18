package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;


@Component
final class NoteDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<NoteDomainModel, NotePersistenceModel>
{
	private final ModelBuilderFactory<NoteDomainModel,
			NoteDomainModel.NoteDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<NotePersistenceModel,
			NotePersistenceModel.NotePersistenceModelBuilder>
			persistenceModelBuilderFactory;


	@Override
	public NotePersistenceModel toPersistenceModel(final NoteDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
		                                     .withNote(domainModel.note())
		                                     .build();
	}

	@Override
	public NoteDomainModel toDomainModel(final NotePersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
		                                .withNote(persistenceModel.note())
		                                .build();
	}

	@Override
	public NotePersistenceModel updatePersistenceModel(
			final NotePersistenceModel persistenceModel,
			final NoteDomainModel domainModel)
	{
		persistenceModel.setNote(domainModel.note());
		return persistenceModel;
	}

	NoteDomainPersistenceModelAdapter(
			final ModelBuilderFactory<NoteDomainModel,
					NoteDomainModel.NoteDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<NotePersistenceModel,
					NotePersistenceModel.NotePersistenceModelBuilder> persistenceModelBuilderFactory)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
	}
}