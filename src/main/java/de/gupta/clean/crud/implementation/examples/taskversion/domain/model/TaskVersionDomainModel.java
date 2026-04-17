package de.gupta.clean.crud.implementation.examples.taskversion.domain.model;

import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

public interface TaskVersionDomainModel extends
		BaseDomainModel, TaskVersionModel
{
	interface TaskVersionDomainModelBuilder
			extends TaskVersionModel.TaskVersionModelBuilder<TaskVersionDomainModel, TaskVersionDomainModelBuilder>
	{
	}
}