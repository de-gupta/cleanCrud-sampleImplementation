package de.gupta.clean.crud.implementation.examples.version.useCases.crud.fetch.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionPersistenceModelImpl;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.repository.AbstractPersistenceModelJpaFetchRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class VersionPersistenceModelJpaFetchRepository
		extends
		AbstractPersistenceModelJpaFetchRepository<VersionPersistenceModel, UUID, VersionPersistenceModelImpl>
		implements FetchPersistenceModelRepository<VersionPersistenceModel, UUID>
{
	VersionPersistenceModelJpaFetchRepository(final VersionJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}