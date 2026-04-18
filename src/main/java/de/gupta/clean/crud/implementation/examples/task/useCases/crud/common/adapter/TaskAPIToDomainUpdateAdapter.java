package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<TaskAPIModelUpdatePatch, TaskDomainModelUpdatePatch>
{
	@Override
	public TaskDomainModelUpdatePatch mapToDomainModelUpdatePatch(final TaskAPIModelUpdatePatch apiModel)
	{
		return new TaskDomainModelUpdatePatch(
				apiModel.title(),
				apiModel.description(),
				apiModel.versions(),
				apiModel.removeVersionIds());
	}
}
