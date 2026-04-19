package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.update.api.web;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.security.TagEndpointSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.AbstractSpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.api.web.SpringRestUpdateController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tag Update", description = "Update Operations pertaining to Tag")
@RestController
@RequestMapping("/tag/update")
@EndpointSecurityConfiguration(enabled = true, endpointPolicy = TagEndpointSecurityPolicy.class)
class TagSpringRestUpdateController extends
		AbstractSpringRestUpdateController<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long>
		implements SpringRestUpdateController<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long>
{
	TagSpringRestUpdateController(
			final UpdateServiceFacade<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long> service)
	{
		super(service);
	}
}