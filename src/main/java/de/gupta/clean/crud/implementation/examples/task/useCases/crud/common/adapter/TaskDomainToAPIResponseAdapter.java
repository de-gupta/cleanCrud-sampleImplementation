package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainToAPIResponseAdapter
		implements DomainToAPIResponseAdapter<TaskAPIModelResponse, Long, TaskDomainModelResponse>
{
	private final APIDomainIDAdapter<Long, Long> idAdapter;

	@Override
	public TaskAPIModelResponse mapToAPIModelResponse(final IdentifiedModel<Long, TaskDomainModelResponse> domainModel)
	{
		return TaskAPIModelResponse.of(
				idAdapter.mapToAPIModelID(domainModel.id()),
				domainModel.model().title(),
				domainModel.model().description(),
				domainModel.model().versions(),
				domainModel.model().notes());
	}

	TaskDomainToAPIResponseAdapter(final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		this.idAdapter = idAdapter;
	}
}
