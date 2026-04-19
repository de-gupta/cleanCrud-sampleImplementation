package de.gupta.clean.crud.implementation.examples.tag.domain.model;

import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

public interface TagDomainModel extends
		BaseDomainModel, TagModel
{
	interface TagDomainModelBuilder extends TagModelBuilder<TagDomainModel, TagDomainModelBuilder>
	{
	}
}