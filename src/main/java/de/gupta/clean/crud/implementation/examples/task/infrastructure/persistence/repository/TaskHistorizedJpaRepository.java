package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.repository.AbstractHistorizedPersistenceModelJpaRepository;
import de.gupta.clean.crud.template.useCases.crud.all.infrastructure.persistence.service.PersistenceModelCrudRepository;
import de.gupta.clean.crud.template.useCases.crud.delete.infrastructure.persistence.service.DeletePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("taskDeletePersistenceModelRepository")
final class TaskHistorizedJpaRepository
		extends AbstractHistorizedPersistenceModelJpaRepository<TaskPersistenceModel, UUID, TaskPersistenceModelImpl,
		TaskPersistenceModelHistory>
		implements PersistenceModelCrudRepository<TaskPersistenceModel, UUID>,
		SavePersistenceModelRepository<TaskPersistenceModel>,
		DeletePersistenceModelRepository<UUID>,
		FetchPersistenceModelRepository<TaskPersistenceModel, UUID>
{
	TaskHistorizedJpaRepository(
			final TaskJpaRepository jpaRepository,
			final TaskPersistenceModelHistoryJpaRepository historyRepository,
			final TaskPersistenceHistorySnapshotFactory snapshotFactory)
	{
		super(jpaRepository, historyRepository, snapshotFactory);
	}
}
