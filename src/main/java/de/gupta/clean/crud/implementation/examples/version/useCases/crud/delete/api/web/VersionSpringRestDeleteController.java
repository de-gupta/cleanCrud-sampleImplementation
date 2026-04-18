package de.gupta.clean.crud.implementation.examples.version.useCases.crud.delete.api.web;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.security.VersionEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.AbstractSpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.SpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Version Delete", description = "Delete Operations pertaining to Version")
@RestController
@RequestMapping("/version/delete")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = VersionEndpointSecurityPolicy.class)
class VersionSpringRestDeleteController extends AbstractSpringRestDeleteController<Long>
		implements SpringRestDeleteController<Long>
{
	VersionSpringRestDeleteController(
			@Qualifier("versionDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}