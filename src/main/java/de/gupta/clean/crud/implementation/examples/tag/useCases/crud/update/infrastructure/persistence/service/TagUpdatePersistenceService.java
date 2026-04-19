package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.update.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.AbstractUpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TagUpdatePersistenceService
		extends AbstractUpdatePersistenceService<Long, TagDomainModel, UUID, TagPersistenceModel>
		implements UpdatePersistenceService<Long, TagDomainModel>
{
	TagUpdatePersistenceService(
			final FetchPersistenceModelRepository<TagPersistenceModel, UUID> fetchRepository,
			final SavePersistenceModelRepository<TagPersistenceModel> saveRepository,
			final UpdatePersistenceModelRepository<TagPersistenceModel> updateRepository,
			final DomainPersistenceModelAdapter<TagDomainModel, TagPersistenceModel> modelAdapter,
			@Qualifier("tagDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("tagDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, saveRepository, updateRepository, modelAdapter, idAdapter, idManagement);
	}
}
