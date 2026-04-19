package de.gupta.clean.crud.implementation.examples.note.useCases.crud.delete.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
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
@Qualifier("noteDeletePersistenceService")
final class NoteDeletePersistenceService extends AbstractDeletePersistenceService<Long, UUID, NotePersistenceModel>
		implements DeletePersistenceService<Long>
{
	NoteDeletePersistenceService(
			final FetchPersistenceModelRepository<NotePersistenceModel, UUID> fetchRepository,
			@Qualifier("noteDeletePersistenceModelRepository") final DeletePersistenceModelRepository<UUID> deleteRepository,
			@Qualifier("noteDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("noteDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, deleteRepository, idAdapter, idManagement);
	}
}
