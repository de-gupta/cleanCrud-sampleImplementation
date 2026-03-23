package de.gupta.clean.crud.implementation.examples.task.useCases.crud.fetch.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelImpl;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.repository.AbstractPersistenceModelJpaFetchRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskPersistenceModelJpaFetchRepository
		extends AbstractPersistenceModelJpaFetchRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl>
		implements FetchPersistenceModelRepository<TaskPersistenceModel, UUID>
{
	TaskPersistenceModelJpaFetchRepository(final TaskJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}