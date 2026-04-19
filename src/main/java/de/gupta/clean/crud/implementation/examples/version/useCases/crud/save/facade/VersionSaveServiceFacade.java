package de.gupta.clean.crud.implementation.examples.version.useCases.crud.save.facade;

import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.save.facade.AbstractSaveServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionSaveServiceFacade extends
		AbstractSaveServiceFacade<VersionAPIModelCreate, VersionAPIModelResponse,
				VersionDomainModelCreate, VersionDomainModelResponse, Long>
		implements SaveServiceFacade<VersionAPIModelCreate, VersionAPIModelResponse>
{
	VersionSaveServiceFacade(
			final SaveService<VersionDomainModelCreate, VersionDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<VersionAPIModelCreate, VersionDomainModelCreate> createMapper,
			final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> responseMapper)
	{
		super(service, createMapper, responseMapper);
	}
}