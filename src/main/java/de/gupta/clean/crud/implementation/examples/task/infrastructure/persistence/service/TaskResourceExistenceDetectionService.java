package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.template.domain.service.existence.ResourceExistenceDetectionService;
import org.springframework.stereotype.Component;

@Component
final class TaskResourceExistenceDetectionService implements ResourceExistenceDetectionService<TaskDomainModel>
{
	private final TaskJpaRepository repository;

	@Override
	public boolean existsByModel(final TaskDomainModel domainModel)
	{
		return repository.existsByTitle(domainModel.title());
	}

	TaskResourceExistenceDetectionService(final TaskJpaRepository repository)
	{
		this.repository = repository;
	}
}