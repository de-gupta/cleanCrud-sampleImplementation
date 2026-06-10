package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.RegisterTagCreation;
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
class TagCreationConfiguration
{
	@Bean
	@Qualifier("tagRegisterCreationHandler")
	RegisteredCreationHandler<TagDomainModelCreate, RegisterTagCreation> tagRegisterCreationHandler(
			final AggregateCreationHandler<TagDomainModelCreate, RegisterTagCreation> handler)
	{
		return RegisteredCreationHandler.of(RegisterTagCreation.class, handler);
	}

	@Bean
	@Qualifier("tagCreationHandlerRegistry")
	CreationHandlerRegistry<TagDomainModelCreate> tagCreationHandlerRegistry(
			@Qualifier("tagRegisterCreationHandler") final RegisteredCreationHandler<TagDomainModelCreate, RegisterTagCreation> registerTagCreationHandler)
	{
		return CreationHandlerRegistry.of(List.of(registerTagCreationHandler));
	}

	@Bean
	@Qualifier("tagCreationService")
	QuarantinableCreationService<Long, TagDomainModel> tagCreationService(
			@Qualifier("tagAggregateCrudDefinition") final AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch,
					TagDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("tagCreationHandlerRegistry") final CreationHandlerRegistry<TagDomainModelCreate> handlerRegistry)
	{
		return AggregateCreationServices.creationService("tag-creation", definition, aggregateLifecycleEngine,
				handlerRegistry);
	}

	@Bean
	@Qualifier("tagCreationApplicationController")
	CreationApplicationController<Long, TagDomainModel> tagCreationApplicationController(
			@Qualifier("tagCreationService") final QuarantinableCreationService<Long, TagDomainModel> service)
	{
		return CreationApplicationControllers.controller(service);
	}
}