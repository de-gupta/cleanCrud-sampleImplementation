package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.model;

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
	private final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse>
			versionDomainResponseBuilder;
	private final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse>
			versionDomainToAPIResponseAdapter;

	@Override
	public TaskPersistenceModel toPersistenceModel(final TaskDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
											 .withTitle(domainModel.title())
											 .withDescription(domainModel.description())
											 .withVersionId(domainModel.versions().stream().findFirst()
											                           .map(VersionAPIModelResponse::id))
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

	TaskDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder> persistenceModelBuilderFactory,
			@Qualifier("versionAggregateFetchPort") final AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort,
			@Qualifier("versionDomainResponseBuilder") final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> versionDomainResponseBuilder,
			@Qualifier("versionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<VersionAPIModelResponse, Long, VersionDomainModelResponse> versionDomainToAPIResponseAdapter)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
		this.versionAggregateFetchPort = versionAggregateFetchPort;
		this.versionDomainResponseBuilder = versionDomainResponseBuilder;
		this.versionDomainToAPIResponseAdapter = versionDomainToAPIResponseAdapter;
	}
}