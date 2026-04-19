package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskAPIToDomainCreateAdapter implements APIToDomainCreateAdapter<TaskAPIModelCreate, TaskDomainModelCreate>
{
	@Override
	public TaskDomainModelCreate mapToDomainModelCreate(final TaskAPIModelCreate apiModel)
	{
		return new TaskDomainModelCreate(
				apiModel.title(),
				apiModel.description(),
				apiModel.versions(),
				apiModel.notes());
	}
}
