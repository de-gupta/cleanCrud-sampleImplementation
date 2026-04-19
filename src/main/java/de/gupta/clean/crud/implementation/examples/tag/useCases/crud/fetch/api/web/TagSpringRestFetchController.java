package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.fetch.api.web;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.security.TagEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.AbstractSpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.web.SpringRestFetchController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tag Fetch", description = "Fetch Operations pertaining to Tag")
@RestController
@RequestMapping("/tag/fetch")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TagEndpointSecurityPolicy.class)
class TagSpringRestFetchController extends
		AbstractSpringRestFetchController<TagAPIModelResponse, Long>
		implements SpringRestFetchController<TagAPIModelResponse, Long>
{
	TagSpringRestFetchController(final FetchServiceFacade<TagAPIModelResponse, Long> service)
	{
		super(service);
	}
}