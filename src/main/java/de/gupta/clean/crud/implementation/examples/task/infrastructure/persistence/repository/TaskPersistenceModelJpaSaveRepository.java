package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskPersistenceModelJpaSaveRepository extends
		AbstractPersistenceModelJpaSaveRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl>
		implements SavePersistenceModelRepository<TaskPersistenceModel>
{
	TaskPersistenceModelJpaSaveRepository(final TaskJpaRepository jpaRepository)
	{
		super(jpaRepository);
	}
}