package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.fetch.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.AbstractFetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TagFetchPersistenceService
		extends
		AbstractFetchPersistenceService<Long, TagDomainModel,
				UUID, TagPersistenceModel>
		implements
		FetchPersistenceService<Long, TagDomainModel>
{
	TagFetchPersistenceService(
			final FetchPersistenceModelRepository<TagPersistenceModel, UUID> repository,
			final DomainPersistenceModelAdapter<TagDomainModel,
					TagPersistenceModel> modelAdapter,
			@Qualifier("tagDomainPersistenceIDAdapter") final
			DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, modelAdapter, idAdapter);
	}
}