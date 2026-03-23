package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.*;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskPersistenceModelJpaUpdateRepository
		extends AbstractPersistenceModelJpaUpdateRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl,
		TaskPersistenceModelHistory>
		implements UpdatePersistenceModelRepository<TaskPersistenceModel>
{
	public TaskPersistenceModelJpaUpdateRepository(
			final TaskJpaRepository jpaRepository,
			final TaskPersistenceModelHistoryJpaRepository historyRepository,
			final TaskPersistenceHistorySnapshotFactory snapshotFactory)
	{
		super(jpaRepository, historyRepository, snapshotFactory);
	}
}