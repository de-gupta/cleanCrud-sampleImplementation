package de.gupta.clean.crud.implementation.examples.version.useCases.crud.save.api.application;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.AbstractSaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.SaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class VersionSaveApplicationController extends
		AbstractSaveApplicationController<VersionAPIModelCreate, VersionAPIModelResponse>
		implements SaveApplicationController<VersionAPIModelCreate,
		VersionAPIModelResponse>
{
	VersionSaveApplicationController(
			final SaveServiceFacade<VersionAPIModelCreate, VersionAPIModelResponse> service)
	{
		super(service);
	}
}