package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.crud.aggregate.service.AggregateCrudServices;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeleteService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TaskVersionCrudServicesConfiguration
{
	@Bean
	SaveService<TaskVersionDomainModelCreate, TaskVersionDomainModelResponse, Long> taskVersionSaveService(
			@Qualifier("taskVersionAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch,
					TaskVersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.saveService(definition, aggregateLifecycleEngine);
	}

	@Bean
	FetchService<TaskVersionDomainModel, Long> taskVersionFetchService(
			@Qualifier("taskVersionAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch,
					TaskVersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.fetchService(definition, aggregateLifecycleEngine);
	}

	@Bean
	UpdateService<TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch, TaskVersionDomainModelResponse, Long> taskVersionUpdateService(
			@Qualifier("taskVersionAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch,
					TaskVersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.updateService(definition, aggregateLifecycleEngine);
	}

	@Bean
	@Qualifier("taskVersionDeleteService")
	DeleteService<Long> taskVersionDeleteService(
			@Qualifier("taskVersionAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch,
					TaskVersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.deleteService(definition, aggregateLifecycleEngine);
	}
}
