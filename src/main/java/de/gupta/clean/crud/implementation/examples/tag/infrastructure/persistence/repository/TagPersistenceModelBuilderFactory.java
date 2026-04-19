package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TagPersistenceModelBuilderFactory
		implements ModelBuilderFactory<TagPersistenceModel, TagPersistenceModel.TagPersistenceModelBuilder>
{
	@Override
	public TagPersistenceModel.TagPersistenceModelBuilder builder()
	{
		return TagPersistenceModelImpl.builder();
	}
}