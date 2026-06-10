package de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.domain.RegisterTagIncantation;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.incantation.aggregate.service.AggregateIncantationServices;
import de.gupta.clean.crud.template.useCases.incantation.api.application.IncantationApplicationController;
import de.gupta.clean.crud.template.useCases.incantation.api.application.IncantationApplicationControllers;
import de.gupta.clean.crud.template.useCases.incantation.application.service.IncantationService;
import de.gupta.clean.crud.template.useCases.incantation.domain.handler.IncantationHandler;
import de.gupta.clean.crud.template.useCases.incantation.domain.handler.IncantationHandlerRegistry;
import de.gupta.clean.crud.template.useCases.incantation.domain.handler.RegisteredIncantationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class TagIncantationConfiguration
{
	@Bean
	@Qualifier("tagRegisterIncantationHandler")
	RegisteredIncantationHandler<TagDomainModelCreate, RegisterTagIncantation> tagRegisterIncantationHandler(
			final IncantationHandler<TagDomainModelCreate, RegisterTagIncantation> handler)
	{
		return RegisteredIncantationHandler.of(RegisterTagIncantation.class, handler);
	}

	@Bean
	@Qualifier("tagIncantationHandlerRegistry")
	IncantationHandlerRegistry<TagDomainModelCreate> tagIncantationHandlerRegistry(
			@Qualifier("tagRegisterIncantationHandler") final RegisteredIncantationHandler<TagDomainModelCreate, RegisterTagIncantation> registerTagIncantationHandler)
	{
		return IncantationHandlerRegistry.of(List.of(registerTagIncantationHandler));
	}

	@Bean
	@Qualifier("tagIncantationService")
	IncantationService<Long, TagDomainModel> tagIncantationService(
			@Qualifier("tagAggregateCrudDefinition") final AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch,
					TagDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("tagIncantationHandlerRegistry") final IncantationHandlerRegistry<TagDomainModelCreate> handlerRegistry)
	{
		return AggregateIncantationServices.incantationService(definition, aggregateLifecycleEngine, handlerRegistry);
	}

	@Bean
	@Qualifier("tagIncantationApplicationController")
	IncantationApplicationController<Long, TagDomainModel> tagIncantationApplicationController(
			@Qualifier("tagIncantationService") final IncantationService<Long, TagDomainModel> service)
	{
		return IncantationApplicationControllers.controller(service);
	}
}
