package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilder;
import de.gupta.clean.crud.template.infrastructure.persistence.model.BasePersistenceModel;

import java.util.UUID;

public interface TagPersistenceModel extends BasePersistenceModel<UUID>, TagModel
{
	String name();

	void setName(final String name);

	interface TagPersistenceModelBuilder extends TagModelBuilder<TagPersistenceModel, TagPersistenceModelBuilder>,
			ModelBuilder<TagPersistenceModel>
	{
	}
}