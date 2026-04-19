package de.gupta.clean.crud.implementation.examples.version.useCases.crud.fetch.api.application;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.AbstractFetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.FetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionFetchApplicationController extends
		AbstractFetchApplicationController<VersionAPIModelResponse, Long>
		implements FetchApplicationController<VersionAPIModelResponse, Long>
{
	VersionFetchApplicationController(final FetchServiceFacade<VersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}