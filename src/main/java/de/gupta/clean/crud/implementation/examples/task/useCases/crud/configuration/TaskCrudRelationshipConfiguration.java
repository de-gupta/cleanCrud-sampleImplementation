package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.domain.model.exceptions.operation.InvalidRequestException;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.AggregateRelationshipDefinitions;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.LifecycleSemanticsBuilder;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.intent.SatelliteCreateIntent;
import de.gupta.clean.crud.template.useCases.crud.aggregate.intent.SatelliteMutationIntent;
import de.gupta.clean.crud.template.useCases.crud.aggregate.lifecycle.LifecycleSemantics;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.*;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Configuration
class TaskCrudRelationshipConfiguration
{
	@Bean
	@Qualifier("versionLifecycleSemantics")
	LifecycleSemantics versionLifecycleSemantics()
	{
		return LifecycleSemanticsBuilder.lifecycleSemantics()
		                                .cascadeCreate()
		                                .cascadeUpdate()
		                                .cascadeDelete()
		                                .orphanDelete()
		                                .hydrateOnFetch()
		                                .build();
	}

	@Bean
	@Qualifier("versionCreateInputResolver")
	SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, VersionDomainModelCreate>>> versionCreateInputResolver()
	{
		return taskDomainModelCreate -> taskDomainModelCreate.versions()
		                                                     .stream()
		                                                     .<SatelliteCreateIntent<Long, VersionDomainModelCreate>>map(
																	 version -> new SatelliteCreateIntent.InlineSatelliteCreateIntent<>(
																			 VersionDomainModelCreate.of(
																					 version.version())))
		                                                     .toList();
	}

	@Bean
	@Qualifier("versionPatchInputResolver")
	SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, VersionDomainModelCreate, VersionDomainModelUpdatePatch>>> versionPatchInputResolver()
	{
		return taskDomainModelUpdatePatch ->
		{
			var intents = new ArrayList<SatelliteMutationIntent<Long, VersionDomainModelCreate,
					VersionDomainModelUpdatePatch>>();
			taskDomainModelUpdatePatch.versions().ifPresent(versions -> versions.forEach(version ->
			{
				if (version.id().isPresent())
				{
					intents.add(new SatelliteMutationIntent.UpdateSatelliteMutationIntent<>(
							version.id().orElseThrow(),
							VersionDomainModelUpdatePatch.of(version.version())));
					return;
				}
				if (version.version().isEmpty())
				{
					throw InvalidRequestException.withMessage(
							"An id-less version mutation requires a version payload");
				}
				intents.add(new SatelliteMutationIntent.UpsertCurrentSatelliteMutationIntent<>(
						VersionDomainModelCreate.of(version.version().orElseThrow()),
						VersionDomainModelUpdatePatch.of(version.version())));
			}));
			taskDomainModelUpdatePatch.removeVersionIds().forEach(
					versionId -> intents.add(new SatelliteMutationIntent.RemoveSatelliteMutationIntent<>(versionId)));
			return intents;
		};
	}

	@Bean
	@Qualifier("versionIdentityResolver")
	SatelliteIdentityResolver<TaskDomainModel, VersionDomainModel, Long> versionIdentityResolver()
	{
		return (taskDomainModel, _) -> taskDomainModel.versions().stream()
		                                              .findFirst()
		                                              .map(VersionAPIModelResponse::id);
	}

	@Bean
	@Qualifier("versionLinkStrategy")
	SatelliteLinkStrategy<Long, TaskDomainModel, Long, VersionDomainModel> versionLinkStrategy(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("versionAggregateFetchPort") final de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
			@Qualifier("versionDomainResponseBuilder") final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> versionDomainResponseBuilder,
			@Qualifier("versionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> versionDomainToAPIResponseAdapter)
	{
		return new SatelliteLinkStrategy<>()
		{
			@Override
			public SatellitePersistenceOrder persistenceOrder()
			{
				return SatellitePersistenceOrder.SATELLITE_BEFORE_MASTER;
			}

			@Override
			public Optional<Long> currentLinkedSatelliteDomainId(final TaskDomainModel taskDomainModel)
			{
				return taskDomainModel.versions().stream().findFirst().map(VersionAPIModelResponse::id);
			}

			@Override
			public Collection<Long> currentLinkedSatelliteDomainIds(final TaskDomainModel taskDomainModel)
			{
				return taskDomainModel.versions().stream().map(VersionAPIModelResponse::id).toList();
			}

			@Override
			public TaskDomainModel replaceLinkedSatelliteDomainIds(
					final TaskDomainModel taskDomainModel,
					final Collection<Long> satelliteDomainIds)
			{
				return rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						satelliteDomainIds.stream()
						                  .map(versionId -> toVersionResponse(
												  versionAggregateFetchPort,
												  versionDomainResponseBuilder,
												  versionDomainToAPIResponseAdapter,
												  versionId))
						                  .toList());
			}

			@Override
			public TaskDomainModel attachHydratedSatellites(
					final TaskDomainModel taskDomainModel,
					final Collection<IdentifiedModel<Long, VersionDomainModel>> satellites)
			{
				var hydratedVersion = satellites.stream().findFirst();
				return rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						hydratedVersion.map(version -> versionDomainToAPIResponseAdapter.mapToAPIModelResponse(
										   IdentifiedModel.of(
												   version.id(),
												   versionDomainResponseBuilder.toResponse(version.model()))))
						           .stream()
						           .toList());
			}
		};
	}

	@Bean
	@Qualifier("versionHydrationStrategy")
	SatelliteHydrationStrategy<Long, TaskDomainModel, Long, VersionDomainModel> versionHydrationStrategy()
	{
		return (task, satelliteFetchPort, satelliteLinkStrategy) -> satelliteLinkStrategy.currentLinkedSatelliteDomainIds(
																								 task.model())
		                                                                                 .stream()
		                                                                                 .map(satelliteFetchPort::findById)
		                                                                                 .flatMap(
																								 java.util.Optional::stream)
		                                                                                 .collect(
																								 java.util.stream.Collectors.collectingAndThen(
																										 java.util.stream.Collectors.toList(),
																										 versions ->
																												 versions.isEmpty()
																														 ?
																														 task.model()
																														 :
																														 satelliteLinkStrategy.attachHydratedSatellites(
																																 task.model(),
																																 versions)));
	}

	@Bean
	@Qualifier("versionRelationshipDefinition")
	AggregateRelationshipDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch> versionRelationshipDefinition(
			@Qualifier("versionLifecycleSemantics") final LifecycleSemantics lifecycleSemantics,
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> versionAggregateCrudDefinition,
			@Qualifier("versionCreateInputResolver") final SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, VersionDomainModelCreate>>> createInputResolver,
			@Qualifier("versionPatchInputResolver") final SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, VersionDomainModelCreate,
					VersionDomainModelUpdatePatch>>> patchInputResolver,
			@Qualifier("versionIdentityResolver") final SatelliteIdentityResolver<TaskDomainModel, VersionDomainModel, Long> identityResolver,
			@Qualifier("versionLinkStrategy") final SatelliteLinkStrategy<Long, TaskDomainModel, Long, VersionDomainModel> linkStrategy,
			@Qualifier("versionHydrationStrategy") final SatelliteHydrationStrategy<Long, TaskDomainModel, Long, VersionDomainModel> hydrationStrategy)
	{
		return AggregateRelationshipDefinitions
				.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch>aggregateRelationshipDefinition()
				.name("version")
				.cardinality(Cardinality.ONE)
				.lifecycleSemantics(lifecycleSemantics)
				.satelliteDefinition(versionAggregateCrudDefinition)
				.createInputResolver(createInputResolver)
				.patchInputResolver(patchInputResolver)
				.identityResolver(identityResolver)
				.reconciliationStrategy(ReconciliationStrategy.REPLACE)
				.linkStrategy(linkStrategy)
				.hydrationStrategy(hydrationStrategy)
				.build();
	}

	private TaskDomainModel rebuildTaskDomainModel(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			final TaskDomainModel taskDomainModel,
			final Collection<VersionAPIModelResponse> versions)
	{
		return taskDomainModelBuilderFactory.builder()
		                                    .withTitle(taskDomainModel.title())
		                                    .withDescription(taskDomainModel.description())
		                                    .withVersions(versions)
		                                    .build();
	}

	private VersionAPIModelResponse toVersionResponse(
			final de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
			final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> versionDomainResponseBuilder,
			final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> versionDomainToAPIResponseAdapter,
			final Long versionId)
	{
		var versionDomainModel = versionAggregateFetchPort.findById(versionId)
		                                                          .orElseThrow(
																		  () -> de.gupta.clean.crud.template.domain.model.exceptions.resource.ResourceNotFoundException.withId(
																				  versionId));
		return versionDomainToAPIResponseAdapter.mapToAPIModelResponse(
				IdentifiedModel.of(
						versionDomainModel.id(),
						versionDomainResponseBuilder.toResponse(versionDomainModel.model())));
	}
}
