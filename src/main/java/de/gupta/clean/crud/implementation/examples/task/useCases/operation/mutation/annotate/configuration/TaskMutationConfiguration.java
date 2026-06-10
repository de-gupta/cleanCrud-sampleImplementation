package de.gupta.clean.crud.implementation.examples.task.useCases.operation.mutation.annotate.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.operation.mutation.annotate.domain.AnnotateTaskMutation;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.operation.mutation.aggregate.service.AggregateMutationServices;
import de.gupta.clean.crud.template.useCases.operation.mutation.api.application.MutationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.mutation.api.application.MutationApplicationControllers;
import de.gupta.clean.crud.template.useCases.operation.mutation.application.service.MutationService;
import de.gupta.clean.crud.template.useCases.operation.mutation.application.service.QuarantinableMutationService;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.AggregateMutationHandler;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.MutationHandlerRegistry;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.RegisteredMutationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class TaskMutationConfiguration
{
	@Bean
	@Qualifier("taskAnnotateMutationHandler")
	RegisteredMutationHandler<TaskDomainModel, AnnotateTaskMutation> taskAnnotateMutationHandler(
			final AggregateMutationHandler<TaskDomainModel, AnnotateTaskMutation> handler)
	{
		return RegisteredMutationHandler.ofAggregate(AnnotateTaskMutation.class, handler);
	}

	@Bean
	@Qualifier("taskMutationHandlerRegistry")
	MutationHandlerRegistry<TaskDomainModel> taskMutationHandlerRegistry(
			@Qualifier("taskAnnotateMutationHandler") final RegisteredMutationHandler<TaskDomainModel, AnnotateTaskMutation> annotateTaskMutationHandler)
	{
		return MutationHandlerRegistry.of(List.of(annotateTaskMutationHandler));
	}

	@Bean
	@Qualifier("taskMutationService")
	QuarantinableMutationService<Long, TaskDomainModel> taskMutationService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("taskMutationHandlerRegistry") final MutationHandlerRegistry<TaskDomainModel> handlerRegistry)
	{
		return AggregateMutationServices.mutationService("task-mutation", definition, aggregateLifecycleEngine,
				handlerRegistry);
	}

	@Bean
	@Qualifier("taskMutationApplicationController")
	MutationApplicationController<Long, TaskDomainModel> taskMutationApplicationController(
			@Qualifier("taskMutationService") final MutationService<Long, TaskDomainModel> service)
	{
		return MutationApplicationControllers.controller(service);
	}
}