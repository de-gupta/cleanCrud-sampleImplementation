package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
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
	@Qualifier("taskTaskVersionLifecycleSemantics")
	LifecycleSemantics taskTaskVersionLifecycleSemantics()
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
	@Qualifier("taskTaskVersionCreateInputResolver")
	SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, TaskVersionDomainModelCreate>>> taskTaskVersionCreateInputResolver()
	{
		return taskDomainModelCreate -> taskDomainModelCreate.versions()
		                                                     .stream()
		                                                     .<SatelliteCreateIntent<Long, TaskVersionDomainModelCreate>>map(
																	 version -> new SatelliteCreateIntent.InlineSatelliteCreateIntent<>(
																			 TaskVersionDomainModelCreate.of(
																					 version.version())))
		                                                     .toList();
	}

	@Bean
	@Qualifier("taskTaskVersionPatchInputResolver")
	SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch>>> taskTaskVersionPatchInputResolver()
	{
		return taskDomainModelUpdatePatch ->
		{
			var intents = new ArrayList<SatelliteMutationIntent<Long, TaskVersionDomainModelCreate,
					TaskVersionDomainModelUpdatePatch>>();
			taskDomainModelUpdatePatch.versions().ifPresent(versions -> versions.forEach(version ->
			{
				if (version.id().isPresent())
				{
					intents.add(new SatelliteMutationIntent.UpdateSatelliteMutationIntent<>(
							version.id().orElseThrow(),
							TaskVersionDomainModelUpdatePatch.of(version.version())));
					return;
				}
				if (version.version().isEmpty())
				{
					throw InvalidRequestException.withMessage(
							"An id-less version mutation requires a version payload");
				}
				intents.add(new SatelliteMutationIntent.UpsertCurrentSatelliteMutationIntent<>(
						TaskVersionDomainModelCreate.of(version.version().orElseThrow()),
						TaskVersionDomainModelUpdatePatch.of(version.version())));
			}));
			taskDomainModelUpdatePatch.removeVersionIds().forEach(
					versionId -> intents.add(new SatelliteMutationIntent.RemoveSatelliteMutationIntent<>(versionId)));
			return intents;
		};
	}

	@Bean
	@Qualifier("taskTaskVersionIdentityResolver")
	SatelliteIdentityResolver<TaskDomainModel, TaskVersionDomainModel, Long> taskTaskVersionIdentityResolver()
	{
		return (taskDomainModel, _) -> taskDomainModel.versions().stream()
		                                              .findFirst()
		                                              .map(TaskVersionAPIModelResponse::id);
	}

	@Bean
	@Qualifier("taskTaskVersionLinkStrategy")
	SatelliteLinkStrategy<Long, TaskDomainModel, Long, TaskVersionDomainModel> taskTaskVersionLinkStrategy(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("taskVersionAggregateFetchPort") final de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort<Long, TaskVersionDomainModel> taskVersionAggregateFetchPort,
			@Qualifier("taskVersionDomainResponseBuilder") final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse> taskVersionDomainResponseBuilder,
			@Qualifier("taskVersionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse> taskVersionDomainToAPIResponseAdapter)
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
				return taskDomainModel.versions().stream().findFirst().map(TaskVersionAPIModelResponse::id);
			}

			@Override
			public Collection<Long> currentLinkedSatelliteDomainIds(final TaskDomainModel taskDomainModel)
			{
				return taskDomainModel.versions().stream().map(TaskVersionAPIModelResponse::id).toList();
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
						                  .map(taskVersionId -> toTaskVersionResponse(
												  taskVersionAggregateFetchPort,
												  taskVersionDomainResponseBuilder,
												  taskVersionDomainToAPIResponseAdapter,
												  taskVersionId))
						                  .toList());
			}

			@Override
			public TaskDomainModel attachHydratedSatellites(
					final TaskDomainModel taskDomainModel,
					final Collection<IdentifiedModel<Long, TaskVersionDomainModel>> satellites)
			{
				var taskVersion = satellites.stream().findFirst();
				return rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						taskVersion.map(version -> taskVersionDomainToAPIResponseAdapter.mapToAPIModelResponse(
										   IdentifiedModel.of(
												   version.id(),
												   taskVersionDomainResponseBuilder.toResponse(version.model()))))
						           .stream()
						           .toList());
			}
		};
	}

	@Bean
	@Qualifier("taskTaskVersionHydrationStrategy")
	SatelliteHydrationStrategy<Long, TaskDomainModel, Long, TaskVersionDomainModel> taskTaskVersionHydrationStrategy()
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
																										 taskVersions ->
																												 taskVersions.isEmpty()
																														 ?
																														 task.model()
																														 :
																														 satelliteLinkStrategy.attachHydratedSatellites(
																																 task.model(),
																																 taskVersions)));
	}

	@Bean
	@Qualifier("taskTaskVersionRelationshipDefinition")
	AggregateRelationshipDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch> taskTaskVersionRelationshipDefinition(
			@Qualifier("taskTaskVersionLifecycleSemantics") final LifecycleSemantics lifecycleSemantics,
			@Qualifier("taskVersionAggregateCrudDefinition") final AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch,
					TaskVersionDomainModelResponse> taskVersionAggregateCrudDefinition,
			@Qualifier("taskTaskVersionCreateInputResolver") final SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, TaskVersionDomainModelCreate>>> createInputResolver,
			@Qualifier("taskTaskVersionPatchInputResolver") final SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, TaskVersionDomainModelCreate,
					TaskVersionDomainModelUpdatePatch>>> patchInputResolver,
			@Qualifier("taskTaskVersionIdentityResolver") final SatelliteIdentityResolver<TaskDomainModel, TaskVersionDomainModel, Long> identityResolver,
			@Qualifier("taskTaskVersionLinkStrategy") final SatelliteLinkStrategy<Long, TaskDomainModel, Long, TaskVersionDomainModel> linkStrategy,
			@Qualifier("taskTaskVersionHydrationStrategy") final SatelliteHydrationStrategy<Long, TaskDomainModel, Long, TaskVersionDomainModel> hydrationStrategy)
	{
		return AggregateRelationshipDefinitions
				.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch>aggregateRelationshipDefinition()
				.name("version")
				.cardinality(Cardinality.ONE)
				.lifecycleSemantics(lifecycleSemantics)
				.satelliteDefinition(taskVersionAggregateCrudDefinition)
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
			final Collection<TaskVersionAPIModelResponse> versions)
	{
		return taskDomainModelBuilderFactory.builder()
		                                    .withTitle(taskDomainModel.title())
		                                    .withDescription(taskDomainModel.description())
		                                    .withVersions(versions)
		                                    .build();
	}

	private TaskVersionAPIModelResponse toTaskVersionResponse(
			final de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort<Long, TaskVersionDomainModel> taskVersionAggregateFetchPort,
			final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse> taskVersionDomainResponseBuilder,
			final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse> taskVersionDomainToAPIResponseAdapter,
			final Long taskVersionId)
	{
		var taskVersionDomainModel = taskVersionAggregateFetchPort.findById(taskVersionId)
		                                                          .orElseThrow(
																		  () -> de.gupta.clean.crud.template.domain.model.exceptions.resource.ResourceNotFoundException.withId(
																				  taskVersionId));
		return taskVersionDomainToAPIResponseAdapter.mapToAPIModelResponse(
				IdentifiedModel.of(
						taskVersionDomainModel.id(),
						taskVersionDomainResponseBuilder.toResponse(taskVersionDomainModel.model())));
	}
}