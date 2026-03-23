package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelHistoryJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskPersistenceModelJpaSaveRepository
		extends AbstractPersistenceModelJpaSaveRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl,
		TaskPersistenceModelHistory>
		implements SavePersistenceModelRepository<TaskPersistenceModel>
{
	TaskPersistenceModelJpaSaveRepository(
			final TaskJpaRepository jpaRepository,
			final TaskPersistenceModelHistoryJpaRepository historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TaskPersistenceModel, TaskPersistenceModelHistory> snapshotFactory)
	{
		super(jpaRepository, historyRepository, snapshotFactory);
	}
}