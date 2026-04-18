package de.gupta.clean.crud.implementation.examples.version.useCases.crud.update.api.web;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.security.VersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.AbstractSpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.SpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Version Update", description = "Update Operations pertaining to Version")
@RestController
@RequestMapping("/version/update")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = VersionEndpointSecurityPolicy.class)
class VersionSpringRestUpdateController extends
		AbstractSpringRestUpdateController<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long>
		implements
		SpringRestUpdateController<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long>
{
	VersionSpringRestUpdateController(
			final UpdateServiceFacade<VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}