package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.fetch.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelImpl;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.repository.AbstractPersistenceModelJpaFetchRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskVersionPersistenceModelJpaFetchRepository
		extends
		AbstractPersistenceModelJpaFetchRepository<TaskVersionPersistenceModel, UUID, TaskVersionPersistenceModelImpl>
		implements FetchPersistenceModelRepository<TaskVersionPersistenceModel, UUID>
{
	TaskVersionPersistenceModelJpaFetchRepository(final TaskVersionJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}