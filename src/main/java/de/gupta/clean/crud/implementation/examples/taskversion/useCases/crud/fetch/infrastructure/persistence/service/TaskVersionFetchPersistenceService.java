package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.fetch.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.AbstractFetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TaskVersionFetchPersistenceService
		extends
		AbstractFetchPersistenceService<Long, TaskVersionDomainModel,
				UUID, TaskVersionPersistenceModel>
		implements
		FetchPersistenceService<Long, TaskVersionDomainModel>
{
	TaskVersionFetchPersistenceService(
			final FetchPersistenceModelRepository<TaskVersionPersistenceModel, UUID> repository,
			final DomainPersistenceModelAdapter<TaskVersionDomainModel,
					TaskVersionPersistenceModel> modelAdapter,
			@Qualifier("taskVersionDomainPersistenceIDAdapter") final
			DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, modelAdapter, idAdapter);
	}
}