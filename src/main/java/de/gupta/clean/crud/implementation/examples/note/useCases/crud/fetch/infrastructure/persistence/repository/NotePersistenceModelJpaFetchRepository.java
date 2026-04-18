package de.gupta.clean.crud.implementation.examples.note.useCases.crud.fetch.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NotePersistenceModelImpl;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.repository.AbstractPersistenceModelJpaFetchRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class NotePersistenceModelJpaFetchRepository
		extends AbstractPersistenceModelJpaFetchRepository<NotePersistenceModel, UUID, NotePersistenceModelImpl>
		implements FetchPersistenceModelRepository<NotePersistenceModel, UUID>
{
	NotePersistenceModelJpaFetchRepository(final NoteJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}