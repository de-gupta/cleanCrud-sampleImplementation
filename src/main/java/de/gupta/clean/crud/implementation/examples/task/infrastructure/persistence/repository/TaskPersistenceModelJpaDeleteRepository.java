package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskDeletePersistenceModelRepository")
final class TaskPersistenceModelJpaDeleteRepository
		extends AbstractPersistenceModelJpaDeleteRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl>
		implements DeletePersistenceModelRepository<UUID>
{
	TaskPersistenceModelJpaDeleteRepository(final JpaRepository<TaskPersistenceModelImpl, UUID> jpaRepository)
	{
		super(jpaRepository);
	}
}