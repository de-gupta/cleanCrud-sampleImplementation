package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.save.api.web;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.security.TagEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.AbstractSpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.api.web.SpringRestSaveController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tag Save", description = "Save Operations pertaining to Tag")
@RestController
@RequestMapping("/tag/save")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TagEndpointSecurityPolicy.class)
class TagSpringRestSaveController extends
		AbstractSpringRestSaveController<TagAPIModelCreate, TagAPIModelResponse>
		implements SpringRestSaveController<TagAPIModelCreate,
		TagAPIModelResponse>
{
	TagSpringRestSaveController(final SaveServiceFacade<TagAPIModelCreate, TagAPIModelResponse> service)
	{
		super(service);
	}
}