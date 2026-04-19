package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
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
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
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
		return fullLifecycleSemantics();
	}

	@Bean
	@Qualifier("noteLifecycleSemantics")
	LifecycleSemantics noteLifecycleSemantics()
	{
		return fullLifecycleSemantics();
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
	@Qualifier("noteCreateInputResolver")
	SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, NoteDomainModelCreate>>> noteCreateInputResolver()
	{
		return taskDomainModelCreate -> taskDomainModelCreate.notes()
		                                                     .stream()
		                                                     .<SatelliteCreateIntent<Long, NoteDomainModelCreate>>map(
																	 note -> new SatelliteCreateIntent.InlineSatelliteCreateIntent<>(
																			 NoteDomainModelCreate.of(note.note())))
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
							VersionDomainModelUpdatePatch.of(version.patch().version())));
					return;
				}
				if (version.patch().version().isEmpty())
				{
					throw InvalidRequestException.withMessage(
							"An id-less version mutation requires a version payload");
				}
				intents.add(new SatelliteMutationIntent.UpsertCurrentSatelliteMutationIntent<>(
						VersionDomainModelCreate.of(version.patch().version().orElseThrow()),
						VersionDomainModelUpdatePatch.of(version.patch().version())));
			}));
			taskDomainModelUpdatePatch.removeVersionIds().forEach(
					versionId -> intents.add(new SatelliteMutationIntent.RemoveSatelliteMutationIntent<>(versionId)));
			return intents;
		};
	}

	@Bean
	@Qualifier("notePatchInputResolver")
	SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, NoteDomainModelCreate, NoteDomainModelUpdatePatch>>> notePatchInputResolver()
	{
		return taskDomainModelUpdatePatch ->
		{
			var intents = new ArrayList<SatelliteMutationIntent<Long, NoteDomainModelCreate,
					NoteDomainModelUpdatePatch>>();
			taskDomainModelUpdatePatch.notes().ifPresent(notes -> notes.forEach(note ->
			{
				if (note.id().isPresent())
				{
					if (note.patch().note().isEmpty())
					{
						throw InvalidRequestException.withMessage(
								"A note update with an id requires a note payload");
					}
					intents.add(new SatelliteMutationIntent.UpdateSatelliteMutationIntent<>(
							note.id().orElseThrow(),
							NoteDomainModelUpdatePatch.of(note.patch().note())));
					return;
				}
				if (note.patch().note().isEmpty())
				{
					throw InvalidRequestException.withMessage(
							"An id-less note mutation requires a note payload");
				}
				intents.add(new SatelliteMutationIntent.CreateSatelliteMutationIntent<>(
						NoteDomainModelCreate.of(note.patch().note().orElseThrow())));
			}));
			taskDomainModelUpdatePatch.removeNoteIds().forEach(
					noteId -> intents.add(new SatelliteMutationIntent.RemoveSatelliteMutationIntent<>(noteId)));
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
	@Qualifier("noteIdentityResolver")
	SatelliteIdentityResolver<TaskDomainModel, NoteDomainModel, Long> noteIdentityResolver()
	{
		return (_, _) -> Optional.empty();
	}

	@Bean
	@Qualifier("versionLinkStrategy")
	SatelliteLinkStrategy<Long, TaskDomainModel, Long, VersionDomainModel> versionLinkStrategy(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("versionAggregateFetchPort") final AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
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
						                  .toList(),
						taskDomainModel.notes());
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
						               .toList(),
						taskDomainModel.notes());
			}
		};
	}

	@Bean
	@Qualifier("noteLinkStrategy")
	SatelliteLinkStrategy<Long, TaskDomainModel, Long, NoteDomainModel> noteLinkStrategy(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("noteAggregateFetchPort") final AggregateFetchPort<Long, NoteDomainModel> noteAggregateFetchPort,
			@Qualifier("noteDomainResponseBuilder") final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> noteDomainResponseBuilder,
			@Qualifier("noteDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> noteDomainToAPIResponseAdapter)
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
				return taskDomainModel.notes().stream().findFirst().map(NoteAPIModelResponse::id);
			}

			@Override
			public Collection<Long> currentLinkedSatelliteDomainIds(final TaskDomainModel taskDomainModel)
			{
				return taskDomainModel.notes().stream().map(NoteAPIModelResponse::id).toList();
			}

			@Override
			public TaskDomainModel replaceLinkedSatelliteDomainIds(
					final TaskDomainModel taskDomainModel,
					final Collection<Long> satelliteDomainIds)
			{
				return rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						taskDomainModel.versions(),
						satelliteDomainIds.stream()
						                  .map(noteId -> toNoteResponse(
												  noteAggregateFetchPort,
												  noteDomainResponseBuilder,
												  noteDomainToAPIResponseAdapter,
												  noteId))
						                  .toList());
			}

			@Override
			public TaskDomainModel attachHydratedSatellites(
					final TaskDomainModel taskDomainModel,
					final Collection<IdentifiedModel<Long, NoteDomainModel>> satellites)
			{
				return rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						taskDomainModel.versions(),
						satellites.stream()
						          .map(note -> noteDomainToAPIResponseAdapter.mapToAPIModelResponse(
										  IdentifiedModel.of(
												  note.id(),
												  noteDomainResponseBuilder.toResponse(note.model()))))
						          .toList());
			}
		};
	}

	@Bean
	@Qualifier("versionHydrationStrategy")
	SatelliteHydrationStrategy<Long, TaskDomainModel, Long, VersionDomainModel> versionHydrationStrategy()
	{
		return (task, satelliteFetchPort, satelliteLinkStrategy) -> hydrate(task, satelliteFetchPort,
				satelliteLinkStrategy);
	}

	@Bean
	@Qualifier("noteHydrationStrategy")
	SatelliteHydrationStrategy<Long, TaskDomainModel, Long, NoteDomainModel> noteHydrationStrategy()
	{
		return (task, satelliteFetchPort, satelliteLinkStrategy) -> hydrate(task, satelliteFetchPort,
				satelliteLinkStrategy);
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

	@Bean
	@Qualifier("noteRelationshipDefinition")
	AggregateRelationshipDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch> noteRelationshipDefinition(
			@Qualifier("noteLifecycleSemantics") final LifecycleSemantics lifecycleSemantics,
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch,
					NoteDomainModelResponse> noteAggregateCrudDefinition,
			@Qualifier("noteCreateInputResolver") final SatelliteCreateInputResolver<TaskDomainModelCreate, Collection<SatelliteCreateIntent<Long, NoteDomainModelCreate>>> createInputResolver,
			@Qualifier("notePatchInputResolver") final SatellitePatchInputResolver<TaskDomainModelUpdatePatch, Collection<SatelliteMutationIntent<Long, NoteDomainModelCreate,
					NoteDomainModelUpdatePatch>>> patchInputResolver,
			@Qualifier("noteIdentityResolver") final SatelliteIdentityResolver<TaskDomainModel, NoteDomainModel, Long> identityResolver,
			@Qualifier("noteLinkStrategy") final SatelliteLinkStrategy<Long, TaskDomainModel, Long, NoteDomainModel> linkStrategy,
			@Qualifier("noteHydrationStrategy") final SatelliteHydrationStrategy<Long, TaskDomainModel, Long, NoteDomainModel> hydrationStrategy)
	{
		return AggregateRelationshipDefinitions
				.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch>aggregateRelationshipDefinition()
				.name("note")
				.cardinality(Cardinality.MANY)
				.lifecycleSemantics(lifecycleSemantics)
				.satelliteDefinition(noteAggregateCrudDefinition)
				.createInputResolver(createInputResolver)
				.patchInputResolver(patchInputResolver)
				.identityResolver(identityResolver)
				.reconciliationStrategy(ReconciliationStrategy.MERGE_BY_ID)
				.linkStrategy(linkStrategy)
				.hydrationStrategy(hydrationStrategy)
				.build();
	}

	private LifecycleSemantics fullLifecycleSemantics()
	{
		return LifecycleSemanticsBuilder.lifecycleSemantics()
		                                .cascadeCreate()
		                                .cascadeUpdate()
		                                .cascadeDelete()
		                                .orphanDelete()
		                                .hydrateOnFetch()
		                                .build();
	}

	private <SatelliteDomainId, SatelliteDomainModel, MasterDomainId, MasterDomainModel>
	MasterDomainModel hydrate(
			final IdentifiedModel<MasterDomainId, MasterDomainModel> master,
			final AggregateFetchPort<SatelliteDomainId, SatelliteDomainModel> satelliteFetchPort,
			final SatelliteLinkStrategy<MasterDomainId, MasterDomainModel, SatelliteDomainId, SatelliteDomainModel> satelliteLinkStrategy)
	{
		return satelliteLinkStrategy.currentLinkedSatelliteDomainIds(master.model())
		                            .stream()
		                            .map(satelliteFetchPort::findById)
		                            .flatMap(Optional::stream)
		                            .collect(java.util.stream.Collectors.collectingAndThen(
											java.util.stream.Collectors.toList(),
											satellites -> satellites.isEmpty()
													? master.model()
													: satelliteLinkStrategy.attachHydratedSatellites(
													master.model(),
													satellites)));
	}

	private TaskDomainModel rebuildTaskDomainModel(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			final TaskDomainModel taskDomainModel,
			final Collection<VersionAPIModelResponse> versions,
			final Collection<NoteAPIModelResponse> notes)
	{
		return taskDomainModelBuilderFactory.builder()
		                                    .withTitle(taskDomainModel.title())
		                                    .withDescription(taskDomainModel.description())
		                                    .withVersions(versions)
		                                    .withNotes(notes)
		                                    .build();
	}

	private VersionAPIModelResponse toVersionResponse(
			final AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
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

	private NoteAPIModelResponse toNoteResponse(
			final AggregateFetchPort<Long, NoteDomainModel> noteAggregateFetchPort,
			final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> noteDomainResponseBuilder,
			final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> noteDomainToAPIResponseAdapter,
			final Long noteId)
	{
		var noteDomainModel = noteAggregateFetchPort.findById(noteId)
		                                            .orElseThrow(
				                                            () -> de.gupta.clean.crud.template.domain.model.exceptions.resource.ResourceNotFoundException.withId(
																	noteId));
		return noteDomainToAPIResponseAdapter.mapToAPIModelResponse(
				IdentifiedModel.of(
						noteDomainModel.id(),
						noteDomainResponseBuilder.toResponse(noteDomainModel.model())));
	}
}