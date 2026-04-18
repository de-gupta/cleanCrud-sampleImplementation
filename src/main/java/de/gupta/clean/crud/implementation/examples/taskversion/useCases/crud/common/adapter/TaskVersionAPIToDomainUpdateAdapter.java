package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;


@Component
final class TaskVersionAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<TaskVersionAPIModelUpdatePatch, TaskVersionDomainModelUpdatePatch>
{

	@Override
	public TaskVersionDomainModelUpdatePatch mapToDomainModelUpdatePatch(final TaskVersionAPIModelUpdatePatch apiModel)
	{
		return new TaskVersionDomainModelUpdatePatch(
				apiModel.version()
		);
	}

	TaskVersionAPIToDomainUpdateAdapter()
	{
	}
}
