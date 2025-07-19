package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

public interface TaskDomainModel extends BaseDomainModel, TaskModel
{
	interface TaskDomainModelBuilder extends TaskModelBuilder<TaskDomainModel, TaskDomainModelBuilder>
	{
	}
}