package de.gupta.clean.crud.implementation.examples.version.useCases.crud.save.api.web;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.security.VersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.AbstractSpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.SpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Version Save", description = "Save Operations pertaining to Version")
@RestController
@RequestMapping("/version/save")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = VersionEndpointSecurityPolicy.class)
class VersionSpringRestSaveController extends
		AbstractSpringRestSaveController<VersionAPIModelCreate, VersionAPIModelResponse>
		implements SpringRestSaveController<VersionAPIModelCreate,
		VersionAPIModelResponse>
{
	VersionSpringRestSaveController(
			final SaveServiceFacade<VersionAPIModelCreate, VersionAPIModelResponse> service)
	{
		super(service);
	}
}