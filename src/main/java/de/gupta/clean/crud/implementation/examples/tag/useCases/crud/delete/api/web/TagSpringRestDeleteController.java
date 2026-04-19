package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.delete.api.web;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.security.TagEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.AbstractSpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.SpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tag Delete", description = "Delete Operations pertaining to Tag")
@RestController
@RequestMapping("/tag/delete")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TagEndpointSecurityPolicy.class)
class TagSpringRestDeleteController extends AbstractSpringRestDeleteController<Long>
		implements SpringRestDeleteController<Long>
{
	TagSpringRestDeleteController(
			@Qualifier("tagDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}