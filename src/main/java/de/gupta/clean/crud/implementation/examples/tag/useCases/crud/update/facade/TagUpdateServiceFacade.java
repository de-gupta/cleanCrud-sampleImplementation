package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.update.facade;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.facade.AbstractUpdateServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TagUpdateServiceFacade extends
		AbstractUpdateServiceFacade<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long,
				TagDomainModelCreate, TagDomainModelUpdatePatch, TagDomainModelResponse, Long>
		implements UpdateServiceFacade<TagAPIModelCreate, TagAPIModelUpdatePatch, TagAPIModelResponse, Long>
{
	TagUpdateServiceFacade(
			final UpdateService<TagDomainModelCreate, TagDomainModelUpdatePatch, TagDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TagAPIModelCreate,
					TagDomainModelCreate> createAdapter,
			final APIToDomainUpdateAdapter<TagAPIModelUpdatePatch, TagDomainModelUpdatePatch> updateAdapter,
			final DomainToAPIResponseAdapter<TagAPIModelResponse, Long,
					TagDomainModelResponse> responseAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, createAdapter, updateAdapter, responseAdapter, idAdapter);
	}
}