package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.update.api.application;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.AbstractUpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagUpdateApplicationController extends
		AbstractUpdateApplicationController<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long>
		implements UpdateApplicationController<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long>
{
	TagUpdateApplicationController(
			final UpdateServiceFacade<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long> service)
	{
		super(service);
	}
}