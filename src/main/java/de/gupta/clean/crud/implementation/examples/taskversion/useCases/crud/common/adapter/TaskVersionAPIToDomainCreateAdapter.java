package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import org.springframework.stereotype.Component;


@Component
final class TaskVersionAPIToDomainCreateAdapter
		implements APIToDomainCreateAdapter<TaskVersionAPIModelCreate, TaskVersionDomainModelCreate>
{

	@Override
	public TaskVersionDomainModelCreate mapToDomainModelCreate(final TaskVersionAPIModelCreate apiModel)
	{
		return new TaskVersionDomainModelCreate(
				apiModel.version()
		);
	}

	TaskVersionAPIToDomainCreateAdapter()
	{
	}
}