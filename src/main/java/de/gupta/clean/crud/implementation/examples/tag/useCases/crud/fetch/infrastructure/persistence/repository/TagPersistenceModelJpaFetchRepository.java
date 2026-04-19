package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.fetch.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagPersistenceModelImpl;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.repository.AbstractPersistenceModelJpaFetchRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TagPersistenceModelJpaFetchRepository
		extends AbstractPersistenceModelJpaFetchRepository<TagPersistenceModel, UUID, TagPersistenceModelImpl>
		implements FetchPersistenceModelRepository<TagPersistenceModel, UUID>
{
	TagPersistenceModelJpaFetchRepository(final TagJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}