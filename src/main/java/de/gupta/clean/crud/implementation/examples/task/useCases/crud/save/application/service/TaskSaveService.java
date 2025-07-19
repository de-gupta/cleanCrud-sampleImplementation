package de.gupta.clean.crud.implementation.examples.task.useCases.crud.save.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.AbstractSaveService;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import org.springframework.stereotype.Service;

@Service
final class TaskSaveService extends
		AbstractSaveService<TaskDomainModel, TaskDomainModelCreate, TaskDomainModelResponse, Long>
		implements SaveService<TaskDomainModelCreate, TaskDomainModelResponse, Long>
{
	TaskSaveService(final SavePersistenceService<Long, TaskDomainModel> persistenceService,
					final DomainModelBuilder<TaskDomainModelCreate, TaskDomainModel> modelBuilder,
					final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> responseModelMapper,
					final InsertionPolicy<TaskDomainModel> insertionPolicy,
					final DomainSecurityPolicy<TaskDomainModel> domainSecurityPolicy)
	{
		super(persistenceService, modelBuilder, responseModelMapper, insertionPolicy, domainSecurityPolicy);
	}
}