package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository.TaskVersionJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TaskVersionExistingDomainModelsSupplier
		implements Supplier<Collection<TaskVersionDomainModel>>
{
	private final TaskVersionJpaRepository repository;
	private final DomainPersistenceModelAdapter<TaskVersionDomainModel, TaskVersionPersistenceModel> modelAdapter;

	@Override
	public Collection<TaskVersionDomainModel> get()
	{
		return repository.findAll()
		                 .stream()
		                 .map(modelAdapter::toDomainModel)
		                 .toList();
	}

	TaskVersionExistingDomainModelsSupplier(
			final TaskVersionJpaRepository repository,
			final DomainPersistenceModelAdapter<TaskVersionDomainModel, TaskVersionPersistenceModel> modelAdapter)
	{
		this.repository = repository;
		this.modelAdapter = modelAdapter;
	}
}