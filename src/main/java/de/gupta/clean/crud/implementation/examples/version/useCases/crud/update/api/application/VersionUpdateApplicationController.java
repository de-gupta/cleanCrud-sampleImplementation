package de.gupta.clean.crud.implementation.examples.version.useCases.crud.update.api.application;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.AbstractUpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionUpdateApplicationController extends
		AbstractUpdateApplicationController<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long>
		implements
		UpdateApplicationController<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long>
{
	VersionUpdateApplicationController(
			final UpdateServiceFacade<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}