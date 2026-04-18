package de.gupta.clean.crud.implementation.examples.note.useCases.crud.save.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.AbstractSavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class NoteSavePersistenceService extends
		AbstractSavePersistenceService<Long, NoteDomainModel, UUID, NotePersistenceModel>
		implements SavePersistenceService<Long, NoteDomainModel>
{
	NoteSavePersistenceService(
			final SavePersistenceModelRepository<NotePersistenceModel> repository,
			final DomainPersistenceModelAdapter<NoteDomainModel, NotePersistenceModel> modelAdapter,
			@Qualifier("noteDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(repository, modelAdapter, idManagement);
	}
}
