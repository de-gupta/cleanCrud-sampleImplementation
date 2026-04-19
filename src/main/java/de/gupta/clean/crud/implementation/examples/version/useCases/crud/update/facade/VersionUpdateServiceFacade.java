package de.gupta.clean.crud.implementation.examples.version.useCases.crud.update.facade;

import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.facade.AbstractUpdateServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionUpdateServiceFacade extends
		AbstractUpdateServiceFacade<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long,
				VersionDomainModelCreate, VersionDomainModelUpdatePatch, VersionDomainModelResponse, Long>
		implements
		UpdateServiceFacade<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long>
{
	VersionUpdateServiceFacade(
			final UpdateService<VersionDomainModelCreate, VersionDomainModelUpdatePatch, VersionDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<VersionAPIModelCreate,
					VersionDomainModelCreate> createAdapter,
			final APIToDomainUpdateAdapter<VersionAPIModelUpdatePatch, VersionDomainModelUpdatePatch> updateAdapter,
			final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long,
					VersionDomainModelResponse> responseAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, createAdapter, updateAdapter, responseAdapter, idAdapter);
	}
}