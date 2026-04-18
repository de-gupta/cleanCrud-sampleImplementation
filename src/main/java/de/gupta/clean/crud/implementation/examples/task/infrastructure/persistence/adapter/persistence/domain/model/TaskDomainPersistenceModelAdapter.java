package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
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
	private final AggregateFetchPort<Long, TaskVersionDomainModel> taskVersionAggregateFetchPort;
	private final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse>
			taskVersionDomainResponseBuilder;
	private final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse>
			taskVersionDomainToAPIResponseAdapter;

	@Override
	public TaskPersistenceModel toPersistenceModel(final TaskDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
											 .withTitle(domainModel.title())
											 .withDescription(domainModel.description())
											 .withTaskVersionId(domainModel.versions().stream().findFirst()
											                               .map(TaskVersionAPIModelResponse::id))
											 .build();
	}

	@Override
	public TaskDomainModel toDomainModel(final TaskPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
										.withTitle(persistenceModel.title())
										.withDescription(persistenceModel.description())
										.withVersions(
												persistenceModel.taskVersionId().flatMap(this::taskVersion).stream()
												                .toList())
										.build();
	}

	@Override
	public TaskPersistenceModel updatePersistenceModel(final TaskPersistenceModel persistenceModel,
													   final TaskDomainModel domainModel)
	{
		persistenceModel.setTitle(domainModel.title());
		persistenceModel.setDescription(domainModel.description().orElse(null));
		persistenceModel.setTaskVersionId(
				domainModel.versions().stream().findFirst().map(TaskVersionAPIModelResponse::id).orElse(null));

		return persistenceModel;
	}

	private java.util.Optional<TaskVersionAPIModelResponse> taskVersion(final Long taskVersionId)
	{
		return taskVersionAggregateFetchPort.findById(taskVersionId)
		                                    .map(taskVersionDomainModel -> IdentifiedModel.of(
													taskVersionId,
													taskVersionDomainResponseBuilder.toResponse(
															taskVersionDomainModel.model())))
		                                    .map(taskVersionDomainToAPIResponseAdapter::mapToAPIModelResponse);
	}

	TaskDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder> persistenceModelBuilderFactory,
			@Qualifier("taskVersionAggregateFetchPort") final AggregateFetchPort<Long, TaskVersionDomainModel> taskVersionAggregateFetchPort,
			@Qualifier("taskVersionDomainResponseBuilder") final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse> taskVersionDomainResponseBuilder,
			@Qualifier("taskVersionDomainToAPIResponseAdapter") final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse> taskVersionDomainToAPIResponseAdapter)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
		this.taskVersionAggregateFetchPort = taskVersionAggregateFetchPort;
		this.taskVersionDomainResponseBuilder = taskVersionDomainResponseBuilder;
		this.taskVersionDomainToAPIResponseAdapter = taskVersionDomainToAPIResponseAdapter;
	}
}