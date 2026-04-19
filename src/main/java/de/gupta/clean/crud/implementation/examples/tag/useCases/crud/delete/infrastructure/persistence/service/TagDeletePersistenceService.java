package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.delete.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.AbstractDeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("tagDeletePersistenceService")
final class TagDeletePersistenceService extends AbstractDeletePersistenceService<Long, UUID, TagPersistenceModel>
		implements DeletePersistenceService<Long>
{
	TagDeletePersistenceService(
			final FetchPersistenceModelRepository<TagPersistenceModel, UUID> fetchRepository,
			@Qualifier("tagDeletePersistenceModelRepository") final DeletePersistenceModelRepository<UUID> deleteRepository,
			@Qualifier("tagDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("tagDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, deleteRepository, idAdapter, idManagement);
	}
}
