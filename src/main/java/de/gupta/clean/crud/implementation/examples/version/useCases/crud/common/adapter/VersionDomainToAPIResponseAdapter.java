package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.stereotype.Component;


@Component
final class VersionDomainToAPIResponseAdapter
		implements DomainToAPIResponseAdapter<VersionAPIModelResponse,
		Long, VersionDomainModelResponse>
{
	private final APIDomainIDAdapter<Long, Long> idAdapter;

	@Override
	public VersionAPIModelResponse mapToAPIModelResponse(
			final IdentifiedModel<Long,
					VersionDomainModelResponse> domainModel)
	{
		return VersionAPIModelResponse.of(
				idAdapter.mapToAPIModelID(domainModel.id()),
				domainModel.model().version()
		);
	}

	VersionDomainToAPIResponseAdapter(
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		this.idAdapter = idAdapter;
	}
}