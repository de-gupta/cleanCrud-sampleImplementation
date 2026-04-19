package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.domain.model.exceptions.operation.InvalidRequestException;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.AggregateRelationshipDefinitions;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.LifecycleSemanticsBuilder;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.lifecycle.LifecycleSemantics;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.AggregateRelationshipDefinition;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
class TaskCrudRelationshipConfiguration
{
	@Bean
	@Qualifier("versionRelationshipDefinition")
	AggregateRelationshipDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch> versionRelationshipDefinition(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("versionAggregateCrudDefinition") final AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
					VersionDomainModelResponse> versionAggregateCrudDefinition,
			@Qualifier("versionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> versionDomainToAPIResponseAdapter)
	{
		return AggregateRelationshipDefinitions
				.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch,
						VersionDomainModelResponse, VersionAPIModelCreate, VersionAPIModelUpdatePatch, VersionAPIModelResponse>oneToOneSatellite(
						"version", versionAggregateCrudDefinition)
				.lifecycleSemantics(fullLifecycleSemantics())
				.createExtractor(taskDomainModelCreate -> extractSingleVersion(taskDomainModelCreate.versions(),
						"Task create allows at most one nested version"))
				.createMapper(version -> VersionDomainModelCreate.of(version.version()))
				.patchExtractor(taskDomainModelUpdatePatch -> taskDomainModelUpdatePatch.versions().map(versions ->
				{
					if (versions.size() > 1)
					{
						throw InvalidRequestException.withMessage(
								"Task version updates allow at most one nested version mutation");
					}
					return versions.stream().findFirst();
				}).orElse(Optional.empty()))
				.patchMapper(version -> VersionDomainModelUpdatePatch.of(version.version()))
				.patchCreateMapper(version -> VersionDomainModelCreate.of(
						version.version().orElseThrow(
								() -> InvalidRequestException.withMessage(
										"An id-less version mutation requires a version payload"))))
				.removeIdExtractor(TaskDomainModelUpdatePatch::removeVersionIds)
				.currentSatellite(taskDomainModel -> taskDomainModel.versions().stream().findFirst())
				.replaceSatellite((taskDomainModel, version) -> rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						version.stream().toList(),
						taskDomainModel.notes()))
				.publicResponseMapper(versionDomainToAPIResponseAdapter::mapToAPIModelResponse)
				.build();
	}

	@Bean
	@Qualifier("noteRelationshipDefinition")
	AggregateRelationshipDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch> noteRelationshipDefinition(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory,
			@Qualifier("noteAggregateCrudDefinition") final AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch,
					NoteDomainModelResponse> noteAggregateCrudDefinition,
			@Qualifier("noteDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> noteDomainToAPIResponseAdapter)
	{
		return AggregateRelationshipDefinitions
				.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch,
						NoteDomainModelResponse, de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate, NoteAPIModelUpdatePatch,
						NoteAPIModelResponse>oneToManySatellite("note", noteAggregateCrudDefinition)
				.lifecycleSemantics(fullLifecycleSemantics())
				.createExtractor(TaskDomainModelCreate::notes)
				.createMapper(note -> NoteDomainModelCreate.of(note.note()))
				.patchExtractor(taskDomainModelUpdatePatch -> taskDomainModelUpdatePatch.notes().orElse(List.of()))
				.patchMapper(note -> NoteDomainModelUpdatePatch.of(note.note()))
				.patchCreateMapper(note -> NoteDomainModelCreate.of(
						note.note().orElseThrow(
								() -> InvalidRequestException.withMessage(
										"An id-less note mutation requires a note payload"))))
				.removeIdExtractor(TaskDomainModelUpdatePatch::removeNoteIds)
				.currentSatellites(TaskDomainModel::notes)
				.replaceSatellites((taskDomainModel, notes) -> rebuildTaskDomainModel(
						taskDomainModelBuilderFactory,
						taskDomainModel,
						taskDomainModel.versions(),
						notes))
				.publicResponseMapper(noteDomainToAPIResponseAdapter::mapToAPIModelResponse)
				.build();
	}

	private Optional<VersionAPIModelCreate> extractSingleVersion(
			final Collection<VersionAPIModelCreate> versions,
			final String message)
	{
		if (versions.size() > 1)
		{
			throw InvalidRequestException.withMessage(message);
		}
		return versions.stream().findFirst();
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
}