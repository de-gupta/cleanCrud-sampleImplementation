package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelHistoryJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskDeletePersistenceModelRepository")
class TaskPersistenceModelJpaDeleteRepository
		extends AbstractPersistenceModelJpaDeleteRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl,
		TaskPersistenceModelHistory>
		implements DeletePersistenceModelRepository<UUID>
{
	TaskPersistenceModelJpaDeleteRepository(
			final TaskJpaRepository jpaRepository,
			final TaskPersistenceModelHistoryJpaRepository historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TaskPersistenceModel, TaskPersistenceModelHistory> snapshotFactory,
			final @Qualifier("taskAuditActorSupplier") AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}