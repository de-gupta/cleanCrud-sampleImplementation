package de.gupta.clean.crud.implementation.examples.note.useCases.crud.fetch.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.AbstractFetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class NoteFetchPersistenceService
		extends
		AbstractFetchPersistenceService<Long, NoteDomainModel,
				UUID, NotePersistenceModel>
		implements
		FetchPersistenceService<Long, NoteDomainModel>
{
	NoteFetchPersistenceService(
			final FetchPersistenceModelRepository<NotePersistenceModel, UUID> repository,
			final DomainPersistenceModelAdapter<NoteDomainModel,
					NotePersistenceModel> modelAdapter,
			@Qualifier("noteDomainPersistenceIDAdapter") final
			DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, modelAdapter, idAdapter);
	}
}