package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<TaskDomainModel, TaskPersistenceModel>
{
	private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder>
			persistenceModelBuilderFactory;

	@Override
	public TaskPersistenceModel toPersistenceModel(final TaskDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
											 .withTitle(domainModel.title())
											 .withDescription(domainModel.description())
											 .build();
	}

	@Override
	public TaskDomainModel toDomainModel(final TaskPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
										.withTitle(persistenceModel.title())
										.withDescription(persistenceModel.description())
										.build();
	}

	@Override
	public TaskPersistenceModel updatePersistenceModel(final TaskPersistenceModel persistenceModel,
													   final TaskDomainModel domainModel)
	{
		persistenceModel.setTitle(domainModel.title());
		domainModel.description().ifPresent(persistenceModel::setDescription);

		return persistenceModel;
	}

	TaskDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TaskPersistenceModel, TaskPersistenceModel.TaskPersistenceModelBuilder> persistenceModelBuilderFactory)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
	}
}