package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.delete.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.repository.AbstractPersistenceModelJpaDeleteRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskVersionDeletePersistenceModelRepository")
class TaskVersionPersistenceModelJpaDeleteRepository
		extends
		AbstractPersistenceModelJpaDeleteRepository<TaskVersionPersistenceModel, UUID, TaskVersionPersistenceModelImpl,
				TaskVersionPersistenceModelHistory>
		implements DeletePersistenceModelRepository<UUID>
{
	TaskVersionPersistenceModelJpaDeleteRepository(
			final TaskVersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TaskVersionPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TaskVersionPersistenceModel, TaskVersionPersistenceModelHistory> snapshotFactory,
			final @Qualifier("taskVersionAuditActorSupplier") AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}