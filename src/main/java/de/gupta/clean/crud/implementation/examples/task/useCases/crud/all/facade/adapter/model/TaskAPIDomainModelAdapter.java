package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.facade.adapter.model;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.all.facade.adapter.model.CrudAPIDomainModelAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskAPIDomainModelAdapter implements
		CrudAPIDomainModelAdapter<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse>
{
	@Override
	public TaskAPIModelResponse mapToWebModelResponse(
			final IdentifiedModel<Long, TaskDomainModelResponse> identifiedDomainModel)
	{
		return TaskAPIModelResponse.of(identifiedDomainModel.id(), identifiedDomainModel.model().title(),
				identifiedDomainModel.model().description());
	}

	@Override
	public TaskDomainModelCreate mapToDomainModelCreate(final TaskAPIModelCreate apiModelCreate)
	{
		return TaskDomainModelCreate.of(apiModelCreate.title(), apiModelCreate.description());
	}

	@Override
	public TaskDomainModelUpdatePatch mapToDomainModelUpdatePatch(final TaskAPIModelUpdatePatch apiModelUpdatePatch)
	{
		return TaskDomainModelUpdatePatch.of(apiModelUpdatePatch.title(), apiModelUpdatePatch.description());
	}
}