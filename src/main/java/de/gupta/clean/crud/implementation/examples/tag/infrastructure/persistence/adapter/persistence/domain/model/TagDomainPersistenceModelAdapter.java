package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;


@Component
final class TagDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<TagDomainModel, TagPersistenceModel>
{
	private final ModelBuilderFactory<TagDomainModel,
			TagDomainModel.TagDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<TagPersistenceModel,
			TagPersistenceModel.TagPersistenceModelBuilder>
			persistenceModelBuilderFactory;


	@Override
	public TagPersistenceModel toPersistenceModel(final TagDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
		                                     .withName(domainModel.name())
		                                     .build();
	}

	@Override
	public TagDomainModel toDomainModel(final TagPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
		                                .withName(persistenceModel.name())
		                                .build();
	}

	@Override
	public TagPersistenceModel updatePersistenceModel(
			final TagPersistenceModel persistenceModel,
			final TagDomainModel domainModel)
	{
		persistenceModel.setName(domainModel.name());
		return persistenceModel;
	}

	TagDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TagDomainModel,
					TagDomainModel.TagDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TagPersistenceModel,
					TagPersistenceModel.TagPersistenceModelBuilder> persistenceModelBuilderFactory)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
	}
}