package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.*;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskPersistenceModelJpaUpdateRepository
		extends AbstractPersistenceModelJpaUpdateRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl,
		TaskPersistenceModelHistory>
		implements UpdatePersistenceModelRepository<TaskPersistenceModel>
{
	public TaskPersistenceModelJpaUpdateRepository(
			final TaskJpaRepository jpaRepository,
			final TaskPersistenceModelHistoryJpaRepository historyRepository,
			final TaskPersistenceHistorySnapshotFactory snapshotFactory,
			@Qualifier("taskAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}