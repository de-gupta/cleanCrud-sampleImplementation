package de.gupta.clean.crud.implementation.examples.version.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
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
class VersionCrudServicesConfiguration
{
	@Bean
	SaveService<VersionDomainModelCreate, VersionDomainModelResponse, Long> versionSaveService(
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.saveService(definition, aggregateLifecycleEngine);
	}

	@Bean
	FetchService<VersionDomainModel, Long> versionFetchService(
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.fetchService(definition, aggregateLifecycleEngine);
	}

	@Bean
	UpdateService<VersionDomainModelCreate, VersionDomainModelUpdatePatch, VersionDomainModelResponse, Long> versionUpdateService(
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.updateService(definition, aggregateLifecycleEngine);
	}

	@Bean
	@Qualifier("versionDeleteService")
	DeleteService<Long> versionDeleteService(
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.deleteService(definition, aggregateLifecycleEngine);
	}
}