package de.gupta.clean.crud.implementation.examples.version.useCases.crud.fetch.api.web;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.security.VersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.AbstractSpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.SpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Version Fetch", description = "Fetch Operations pertaining to Version")
@RestController
@RequestMapping("/version/fetch")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = VersionEndpointSecurityPolicy.class)
class VersionSpringRestFetchController extends
		AbstractSpringRestFetchController<VersionAPIModelResponse, Long>
		implements SpringRestFetchController<VersionAPIModelResponse, Long>
{
	VersionSpringRestFetchController(final FetchServiceFacade<VersionAPIModelResponse, Long> service)
	{
		super(service);
	}
}