package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.save.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.adapter.TriTemporalHistorySnapshotFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.repository.AbstractPersistenceModelJpaSaveRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class TaskVersionPersistenceModelJpaSaveRepository
		extends
		AbstractPersistenceModelJpaSaveRepository<TaskVersionPersistenceModel, UUID, TaskVersionPersistenceModelImpl,
				TaskVersionPersistenceModelHistory>
		implements SavePersistenceModelRepository<TaskVersionPersistenceModel>
{
	TaskVersionPersistenceModelJpaSaveRepository(
			final TaskVersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TaskVersionPersistenceModelHistory> historyRepository,
			final TriTemporalHistorySnapshotFactory<UUID, TaskVersionPersistenceModel, TaskVersionPersistenceModelHistory> snapshotFactory,
			@Qualifier("taskVersionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}