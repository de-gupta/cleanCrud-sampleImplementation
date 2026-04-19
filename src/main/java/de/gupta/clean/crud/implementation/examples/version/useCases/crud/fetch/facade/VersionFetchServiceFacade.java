package de.gupta.clean.crud.implementation.examples.version.useCases.crud.fetch.facade;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.AbstractFetchServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionFetchServiceFacade extends
		AbstractFetchServiceFacade<VersionAPIModelResponse, Long, VersionDomainModel, VersionDomainModelResponse, Long>
		implements FetchServiceFacade<VersionAPIModelResponse,
		Long>
{
	VersionFetchServiceFacade(final FetchService<VersionDomainModel, Long> service,
	                          final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> responseMapper,
	                          final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> responseBuilder,
	                          final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, responseMapper, responseBuilder, idAdapter);
	}
}