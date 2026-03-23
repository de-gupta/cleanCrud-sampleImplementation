package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TaskExistingDomainModelsSupplier implements Supplier<Collection<TaskDomainModel>>
{
	private final TaskJpaRepository repository;
	private final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter;

	@Override
	public Collection<TaskDomainModel> get()
	{
		return repository.findAllAsPersistenceModels()
						 .stream()
						 .map(modelAdapter::toDomainModel)
						 .toList();
	}

	TaskExistingDomainModelsSupplier(
			final TaskJpaRepository repository,
			final DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel> modelAdapter)
	{
		this.repository = repository;
		this.modelAdapter = modelAdapter;
	}
}