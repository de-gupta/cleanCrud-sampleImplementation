package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.save.api.application;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.AbstractSaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.api.application.SaveApplicationController;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagSaveApplicationController extends
		AbstractSaveApplicationController<TagAPIModelCreate, TagAPIModelResponse>
		implements SaveApplicationController<TagAPIModelCreate,
		TagAPIModelResponse>
{
	TagSaveApplicationController(final SaveServiceFacade<TagAPIModelCreate, TagAPIModelResponse> service)
	{
		super(service);
	}
}