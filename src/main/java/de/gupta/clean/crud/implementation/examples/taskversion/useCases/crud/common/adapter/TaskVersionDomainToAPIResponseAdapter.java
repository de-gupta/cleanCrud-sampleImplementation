package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.stereotype.Component;


@Component
final class TaskVersionDomainToAPIResponseAdapter
		implements DomainToAPIResponseAdapter<TaskVersionAPIModelResponse,
		Long, TaskVersionDomainModelResponse>
{
	private final APIDomainIDAdapter<Long, Long> idAdapter;

	@Override
	public TaskVersionAPIModelResponse mapToAPIModelResponse(
			final IdentifiedModel<Long,
					TaskVersionDomainModelResponse> domainModel)
	{
		return TaskVersionAPIModelResponse.of(
				idAdapter.mapToAPIModelID(domainModel.id()),
				domainModel.model().version()
		);
	}

	TaskVersionDomainToAPIResponseAdapter(
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		this.idAdapter = idAdapter;
	}
}