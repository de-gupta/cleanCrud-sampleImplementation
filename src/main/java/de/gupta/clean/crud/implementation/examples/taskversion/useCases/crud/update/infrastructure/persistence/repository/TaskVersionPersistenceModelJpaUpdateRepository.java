package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.update.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionJpaRepository;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceHistorySnapshotFactory;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelHistory;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionPersistenceModelImpl;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.repository.AbstractPersistenceModelJpaUpdateRepository;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskVersionPersistenceModelJpaUpdateRepository
		extends
		AbstractPersistenceModelJpaUpdateRepository<TaskVersionPersistenceModel, UUID, TaskVersionPersistenceModelImpl,
				TaskVersionPersistenceModelHistory>
		implements UpdatePersistenceModelRepository<TaskVersionPersistenceModel>
{
	public TaskVersionPersistenceModelJpaUpdateRepository(
			final TaskVersionJpaRepository jpaRepository,
			final TriTemporalHistoryRepository<UUID, TaskVersionPersistenceModelHistory> historyRepository,
			final TaskVersionPersistenceHistorySnapshotFactory snapshotFactory,
			@Qualifier("taskVersionAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(jpaRepository, historyRepository, snapshotFactory, auditActorSupplier);
	}
}