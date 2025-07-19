package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.AbstractUpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import org.springframework.stereotype.Service;

@Service
final class TaskUpdateService extends
		AbstractUpdateService<TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long>
		implements UpdateService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long>
{
	TaskUpdateService(final FetchPersistenceService<Long, TaskDomainModel> fetchService,
					  final UpdatePersistenceService<Long, TaskDomainModel> persistenceService,
					  final DomainModelBuilder<TaskDomainModelCreate, TaskDomainModel> createModelBuilder,
					  final DomainModelPatcher<TaskDomainModel, TaskDomainModelUpdatePatch> modelPatcher,
					  final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> responseModelMapper,
					  final InsertionPolicy<TaskDomainModel> insertionPolicy,
					  final PatchPolicy<TaskDomainModel> patchPolicy,
					  final DomainSecurityPolicy<TaskDomainModel> domainSecurityPolicy)
	{
		super(fetchService, persistenceService, createModelBuilder, modelPatcher, responseModelMapper, insertionPolicy,
				patchPolicy, domainSecurityPolicy);
	}
}