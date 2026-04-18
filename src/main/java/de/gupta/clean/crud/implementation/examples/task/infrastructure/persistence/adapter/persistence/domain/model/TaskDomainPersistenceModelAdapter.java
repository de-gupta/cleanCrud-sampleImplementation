package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.domain.model.identified.IdentifiedModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel>
{
	private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder>
			persistenceModelBuilderFactory;
	private final AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort;
	private final AggregateFetchPort<Long, NoteDomainModel> noteAggregateFetchPort;
	private final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse>
			versionDomainResponseBuilder;
	private final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> noteDomainResponseBuilder;
	private final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse>
			versionDomainToAPIResponseAdapter;
	private final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse>
			noteDomainToAPIResponseAdapter;

	@Override
	public TaskPersistenceModel toPersistenceModel(final TaskDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
											 .withTitle(domainModel.title())
											 .withDescription(domainModel.description())
											 .withVersionId(domainModel.versions().stream().findFirst()
											                           .map(VersionAPIModelResponse::id))
											 .withNoteIds(domainModel.notes().stream().map(NoteAPIModelResponse::id)
											                         .toList())
											 .build();
	}

	@Override
	public TaskDomainModel toDomainModel(final TaskPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
										.withTitle(persistenceModel.title())
										.withDescription(persistenceModel.description())
										.withVersions(
												persistenceModel.versionId().flatMap(this::version).stream()
												                .toList())
										.withNotes(
												persistenceModel.noteIds().stream()
												                .map(this::note)
												                .flatMap(java.util.Optional::stream)
												                .toList())
										.build();
	}

	@Override
	public TaskPersistenceModel updatePersistenceModel(final TaskPersistenceModel persistenceModel,
													   final TaskDomainModel domainModel)
	{
		persistenceModel.setTitle(domainModel.title());
		persistenceModel.setDescription(domainModel.description().orElse(null));
		persistenceModel.setVersionId(
				domainModel.versions().stream().findFirst().map(VersionAPIModelResponse::id).orElse(null));
		persistenceModel.setNoteIds(domainModel.notes().stream().map(NoteAPIModelResponse::id).toList());

		return persistenceModel;
	}

	private java.util.Optional<VersionAPIModelResponse> version(final Long versionId)
	{
		return versionAggregateFetchPort.findById(versionId)
		                                .map(versionDomainModel -> IdentifiedModel.of(
												versionId,
												versionDomainResponseBuilder.toResponse(
														versionDomainModel.model())))
		                                .map(versionDomainToAPIResponseAdapter::mapToAPIModelResponse);
	}

	private java.util.Optional<NoteAPIModelResponse> note(final Long noteId)
	{
		return noteAggregateFetchPort.findById(noteId)
		                             .map(noteDomainModel -> IdentifiedModel.of(
											 noteId,
											 noteDomainResponseBuilder.toResponse(noteDomainModel.model())))
		                             .map(noteDomainToAPIResponseAdapter::mapToAPIModelResponse);
	}

	TaskDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder> persistenceModelBuilderFactory,
			@Qualifier("versionAggregateFetchPort") final AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
			@Qualifier("noteAggregateFetchPort") final AggregateFetchPort<Long, NoteDomainModel> noteAggregateFetchPort,
			@Qualifier("versionDomainResponseBuilder") final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> versionDomainResponseBuilder,
			@Qualifier("noteDomainResponseBuilder") final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> noteDomainResponseBuilder,
			@Qualifier("versionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> versionDomainToAPIResponseAdapter,
			@Qualifier("noteDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<NoteAPIModelResponse, Long, NoteDomainModelResponse> noteDomainToAPIResponseAdapter)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
		this.versionAggregateFetchPort = versionAggregateFetchPort;
		this.noteAggregateFetchPort = noteAggregateFetchPort;
		this.versionDomainResponseBuilder = versionDomainResponseBuilder;
		this.noteDomainResponseBuilder = noteDomainResponseBuilder;
		this.versionDomainToAPIResponseAdapter = versionDomainToAPIResponseAdapter;
		this.noteDomainToAPIResponseAdapter = noteDomainToAPIResponseAdapter;
	}
}