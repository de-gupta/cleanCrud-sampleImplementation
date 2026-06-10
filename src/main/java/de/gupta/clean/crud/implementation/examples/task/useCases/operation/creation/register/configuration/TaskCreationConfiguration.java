package de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.domain.RegisterTaskCreation;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.operation.creation.aggregate.service.AggregateCreationServices;
import de.gupta.clean.crud.template.useCases.operation.creation.api.application.CreationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.creation.api.application.CreationApplicationControllers;
import de.gupta.clean.crud.template.useCases.operation.creation.application.service.QuarantinableCreationService;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.AggregateCreationHandler;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.CreationHandlerRegistry;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.RegisteredCreationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class TaskCreationConfiguration
{
	@Bean
	@Qualifier("taskRegisterCreationHandler")
	RegisteredCreationHandler<TaskDomainModelCreate, RegisterTaskCreation> taskRegisterCreationHandler(
			final AggregateCreationHandler<TaskDomainModelCreate, RegisterTaskCreation> handler)
	{
		return RegisteredCreationHandler.of(RegisterTaskCreation.class, handler);
	}

	@Bean
	@Qualifier("taskCreationHandlerRegistry")
	CreationHandlerRegistry<TaskDomainModelCreate> taskCreationHandlerRegistry(
			@Qualifier("taskRegisterCreationHandler") final RegisteredCreationHandler<TaskDomainModelCreate, RegisterTaskCreation> registerTaskCreationHandler)
	{
		return CreationHandlerRegistry.of(List.of(registerTaskCreationHandler));
	}

	@Bean
	@Qualifier("taskCreationService")
	QuarantinableCreationService<Long, TaskDomainModel> taskCreationService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("taskCreationHandlerRegistry") final CreationHandlerRegistry<TaskDomainModelCreate> handlerRegistry)
	{
		return AggregateCreationServices.creationService("task-creation", definition, aggregateLifecycleEngine,
				handlerRegistry);
	}

	@Bean
	@Qualifier("taskCreationApplicationController")
	CreationApplicationController<Long, TaskDomainModel> taskCreationApplicationController(
			@Qualifier("taskCreationService") final QuarantinableCreationService<Long, TaskDomainModel> service)
	{
		return CreationApplicationControllers.controller(service);
	}
}
