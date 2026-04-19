package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.save.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.AbstractSavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class TagSavePersistenceService extends
		AbstractSavePersistenceService<Long, TagDomainModel, UUID, TagPersistenceModel>
		implements SavePersistenceService<Long, TagDomainModel>
{
	TagSavePersistenceService(
			final SavePersistenceModelRepository<TagPersistenceModel> repository,
			final DomainPersistenceModelAdapter<TagDomainModel, TagPersistenceModel> modelAdapter,
			@Qualifier("tagDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(repository, modelAdapter, idManagement);
	}
}
