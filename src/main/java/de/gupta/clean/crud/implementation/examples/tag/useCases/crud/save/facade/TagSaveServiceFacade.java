package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.save.facade;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.save.facade.AbstractSaveServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagSaveServiceFacade extends
		AbstractSaveServiceFacade<TagAPIModelCreate, TagAPIModelResponse,
				TagDomainModelCreate, TagDomainModelResponse, Long>
		implements SaveServiceFacade<TagAPIModelCreate, TagAPIModelResponse>
{
	TagSaveServiceFacade(
			final SaveService<TagDomainModelCreate, TagDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TagAPIModelCreate, TagDomainModelCreate> createMapper,
			final DomainToAPIResponseAdapter<TagAPIModelResponse, Long, TagDomainModelResponse> responseMapper)
	{
		super(service, createMapper, responseMapper);
	}
}