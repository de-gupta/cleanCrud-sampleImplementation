package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.repository.AbstractPersistenceModelJpaCrudRepository;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.service.PersistenceModelCrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskPersistenceModelJpaCrudRepository
		extends AbstractPersistenceModelJpaCrudRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl>
		implements PersistenceModelCrudRepository<TaskPersistenceModel, UUID>
{
	TaskPersistenceModelJpaCrudRepository(
			final JpaRepository<TaskPersistenceModelImpl, UUID> jpaRepository)
	{
		super(jpaRepository);
	}
}