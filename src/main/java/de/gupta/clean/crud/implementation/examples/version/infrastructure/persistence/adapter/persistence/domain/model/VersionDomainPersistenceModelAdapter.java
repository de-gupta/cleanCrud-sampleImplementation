package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;


@Component
final class VersionDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<VersionDomainModel, VersionPersistenceModel>
{
	private final ModelBuilderFactory<VersionDomainModel,
			VersionDomainModel.VersionDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<VersionPersistenceModel,
			VersionPersistenceModel.VersionPersistenceModelBuilder>
			persistenceModelBuilderFactory;


	@Override
	public VersionPersistenceModel toPersistenceModel(final VersionDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
		                                     .withVersion(domainModel.version())
		                                     .build();
	}

	@Override
	public VersionDomainModel toDomainModel(final VersionPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
		                                .withVersion(persistenceModel.version())
		                                .build();
	}

	@Override
	public VersionPersistenceModel updatePersistenceModel(
			final VersionPersistenceModel persistenceModel,
			final VersionDomainModel domainModel)
	{
		persistenceModel.setVersion(domainModel.version());
		return persistenceModel;
	}

	VersionDomainPersistenceModelAdapter(
			final ModelBuilderFactory<VersionDomainModel,
					VersionDomainModel.VersionDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<VersionPersistenceModel,
					VersionPersistenceModel.VersionPersistenceModelBuilder> persistenceModelBuilderFactory)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
	}
}