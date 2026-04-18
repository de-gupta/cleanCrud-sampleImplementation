package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

import java.util.Collection;

public interface TaskDomainModel extends BaseDomainModel, TaskModel
{
	Collection<TaskVersionAPIModelResponse> versions();

	interface TaskDomainModelBuilder extends TaskModelBuilder<TaskDomainModel, TaskDomainModelBuilder>
	{
		TaskDomainModelBuilder withVersions(Collection<TaskVersionAPIModelResponse> versions);
	}
}