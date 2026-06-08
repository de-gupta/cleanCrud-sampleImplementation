package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.TaskCreatedPrintTrigger;
import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.TaskPrintPayload;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.crud.aggregate.service.AggregateCrudServices;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeleteService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.process.application.registration.DurableProcessStartRequest;
import de.gupta.clean.crud.template.useCases.process.domain.definition.DurableProcessDefinition;
import de.gupta.clean.crud.template.useCases.process.domain.model.id.CorrelationId;
import de.gupta.clean.crud.template.useCases.process.domain.model.policy.BackoffPolicy;
import de.gupta.clean.crud.template.useCases.process.domain.model.policy.RetryPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collection;

@Configuration
class TaskCrudServicesConfiguration
{
	private static final RetryPolicy TASK_PRINT_RETRY_POLICY =
			new RetryPolicy(3, BackoffPolicy.fixed(Duration.ofMillis(50)));

	@Bean
	SaveService<TaskDomainModelCreate, TaskDomainModelResponse, Long> taskSaveService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("taskPrintProcessDefinition") final DurableProcessDefinition<TaskCreatedPrintTrigger, TaskPrintPayload> taskPrintProcessDefinition)
	{
		return AggregateCrudServices.saveService(
				definition,
				aggregateLifecycleEngine,
				savedModels -> taskPrintStartRequests(savedModels, taskPrintProcessDefinition));
	}

	@Bean
	FetchService<TaskDomainModel, Long> taskFetchService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.fetchService(definition, aggregateLifecycleEngine);
	}

	@Bean
	UpdateService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long> taskUpdateService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.updateService(definition, aggregateLifecycleEngine);
	}

	@Bean
	@Qualifier("taskDeleteService")
	DeleteService<Long> taskDeleteService(
			@Qualifier("taskAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch,
					TaskDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.deleteService(definition, aggregateLifecycleEngine);
	}

	private static Collection<DurableProcessStartRequest<?, ?>> taskPrintStartRequests(
			final Collection<IdentifiedModel<Long, TaskDomainModel>> savedModels,
			final DurableProcessDefinition<TaskCreatedPrintTrigger, TaskPrintPayload> taskPrintProcessDefinition)
	{
		return savedModels.stream()
		                  .map(saved -> new DurableProcessStartRequest<>(
								  taskPrintProcessDefinition,
								  new TaskCreatedPrintTrigger(saved.id()),
								  new TaskPrintPayload(saved.id(), saved.model().title()),
								  new CorrelationId("task-print:" + saved.id()),
								  TASK_PRINT_RETRY_POLICY))
		                  .<DurableProcessStartRequest<?, ?>>map(request -> request)
		                  .toList();
	}
}