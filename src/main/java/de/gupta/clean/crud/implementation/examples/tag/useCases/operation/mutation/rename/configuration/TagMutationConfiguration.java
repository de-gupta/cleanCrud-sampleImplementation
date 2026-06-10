package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.domain.RenameTagMutation;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.operation.mutation.aggregate.service.AggregateMutationServices;
import de.gupta.clean.crud.template.useCases.operation.mutation.api.application.MutationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.mutation.api.application.MutationApplicationControllers;
import de.gupta.clean.crud.template.useCases.operation.mutation.application.service.QuarantinableMutationService;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.AggregateMutationHandler;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.MutationHandlerRegistry;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.RegisteredMutationHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class TagMutationConfiguration
{
	@Bean
	@Qualifier("tagRenameMutationHandler")
	RegisteredMutationHandler<TagDomainModel, RenameTagMutation> tagRenameMutationHandler(
			final AggregateMutationHandler<TagDomainModel, RenameTagMutation> handler)
	{
		return RegisteredMutationHandler.of(RenameTagMutation.class, handler);
	}

	@Bean
	@Qualifier("tagMutationHandlerRegistry")
	MutationHandlerRegistry<TagDomainModel> tagMutationHandlerRegistry(
			@Qualifier("tagRenameMutationHandler") final RegisteredMutationHandler<TagDomainModel, RenameTagMutation> renameTagMutationHandler)
	{
		return MutationHandlerRegistry.of(List.of(renameTagMutationHandler));
	}

	@Bean
	@Qualifier("tagMutationService")
	QuarantinableMutationService<Long, TagDomainModel> tagMutationService(
			@Qualifier("tagAggregateCrudDefinition") final AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch,
					TagDomainModelResponse> definition,
			final AggregateLifecycleEngine aggregateLifecycleEngine,
			@Qualifier("tagMutationHandlerRegistry") final MutationHandlerRegistry<TagDomainModel> handlerRegistry)
	{
		return AggregateMutationServices.mutationService("tag-mutation", definition, aggregateLifecycleEngine,
				handlerRegistry);
	}

	@Bean
	@Qualifier("tagMutationApplicationController")
	MutationApplicationController<Long, TagDomainModel> tagMutationApplicationController(
			@Qualifier("tagMutationService") final QuarantinableMutationService<Long, TagDomainModel> service)
	{
		return MutationApplicationControllers.controller(service);
	}
}
