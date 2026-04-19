package de.gupta.clean.crud.implementation.examples.note.useCases.crud.update.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
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
final class NoteUpdatePersistenceService
		extends AbstractUpdatePersistenceService<Long, NoteDomainModel, UUID, NotePersistenceModel>
		implements UpdatePersistenceService<Long, NoteDomainModel>
{
	NoteUpdatePersistenceService(
			final FetchPersistenceModelRepository<NotePersistenceModel, UUID> fetchRepository,
			final SavePersistenceModelRepository<NotePersistenceModel> saveRepository,
			final UpdatePersistenceModelRepository<NotePersistenceModel> updateRepository,
			final DomainPersistenceModelAdapter<NoteDomainModel, NotePersistenceModel> modelAdapter,
			@Qualifier("noteDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("noteDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, saveRepository, updateRepository, modelAdapter, idAdapter, idManagement);
	}
}
