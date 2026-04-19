package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.fetch.api.application;

import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.AbstractFetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.api.application.FetchApplicationController;
import de.gupta.clean.crud.template.useCases.crud.fetch.facade.FetchServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagFetchApplicationController extends
		AbstractFetchApplicationController<TagAPIModelResponse, Long>
		implements FetchApplicationController<TagAPIModelResponse, Long>
{
	TagFetchApplicationController(final FetchServiceFacade<TagAPIModelResponse, Long> service)
	{
		super(service);
	}
}