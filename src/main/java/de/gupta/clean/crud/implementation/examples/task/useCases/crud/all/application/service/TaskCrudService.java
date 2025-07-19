package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.CrudDomainModelMapper;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.useCases.crud.all.application.service.AbstractCrudService;
import de.gupta.clean.crud.template.useCases.crud.all.application.service.CrudPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.all.application.service.CrudService;
import org.springframework.stereotype.Service;

@Service
final class TaskCrudService extends
		AbstractCrudService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long, TaskDomainModel>
		implements CrudService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long>
{
	TaskCrudService(final CrudPersistenceService<Long, TaskDomainModel> persistenceService,
					final CrudDomainModelMapper<TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse> modelMapper,
					final InsertionPolicy<TaskDomainModel> insertionPolicy,
					final DeletionPolicy<TaskDomainModel> deletionPolicy)
	{
		super(persistenceService, modelMapper, insertionPolicy, deletionPolicy);
	}
}