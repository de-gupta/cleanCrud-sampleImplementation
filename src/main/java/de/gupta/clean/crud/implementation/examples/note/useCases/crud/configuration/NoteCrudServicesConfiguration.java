package de.gupta.clean.crud.implementation.examples.note.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
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
class NoteCrudServicesConfiguration
{
	@Bean
	SaveService<NoteDomainModelCreate, NoteDomainModelResponse, Long> noteSaveService(
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.saveService(definition, aggregateLifecycleEngine);
	}

	@Bean
	FetchService<NoteDomainModel, Long> noteFetchService(
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.fetchService(definition, aggregateLifecycleEngine);
	}

	@Bean
	UpdateService<NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse, Long> noteUpdateService(
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.updateService(definition, aggregateLifecycleEngine);
	}

	@Bean
	@Qualifier("noteDeleteService")
	DeleteService<Long> noteDeleteService(
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine)
	{
		return AggregateCrudServices.deleteService(definition, aggregateLifecycleEngine);
	}
}
