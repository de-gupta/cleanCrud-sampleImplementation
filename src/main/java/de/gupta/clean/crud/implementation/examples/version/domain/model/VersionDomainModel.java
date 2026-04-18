package de.gupta.clean.crud.implementation.examples.version.domain.model;

import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

public interface VersionDomainModel extends
		BaseDomainModel, VersionModel
{
	interface VersionDomainModelBuilder
			extends VersionModel.VersionModelBuilder<VersionDomainModel, VersionDomainModelBuilder>
	{
	}
}