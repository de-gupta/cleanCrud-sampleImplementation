package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.model;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.model.TaskVersionPersistenceModel;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;


@Component
final class TaskVersionDomainPersistenceModelAdapter
		implements DomainPersistenceModelAdapter<TaskVersionDomainModel, TaskVersionPersistenceModel>
{
	private final ModelBuilderFactory<TaskVersionDomainModel,
			TaskVersionDomainModel.TaskVersionDomainModelBuilder>
			domainModelBuilderFactory;
	private final ModelBuilderFactory<TaskVersionPersistenceModel,
			TaskVersionPersistenceModel.TaskVersionPersistenceModelBuilder>
			persistenceModelBuilderFactory;


	@Override
	public TaskVersionPersistenceModel toPersistenceModel(final TaskVersionDomainModel domainModel)
	{
		return persistenceModelBuilderFactory.builder()
		                                     .withVersion(domainModel.version())
		                                     .build();
	}

	@Override
	public TaskVersionDomainModel toDomainModel(final TaskVersionPersistenceModel persistenceModel)
	{
		return domainModelBuilderFactory.builder()
		                                .withVersion(persistenceModel.version())
		                                .build();
	}

	@Override
	public TaskVersionPersistenceModel updatePersistenceModel(
			final TaskVersionPersistenceModel persistenceModel,
			final TaskVersionDomainModel domainModel)
	{
		persistenceModel.setVersion(domainModel.version());
		return persistenceModel;
	}

	TaskVersionDomainPersistenceModelAdapter(
			final ModelBuilderFactory<TaskVersionDomainModel,
					TaskVersionDomainModel.TaskVersionDomainModelBuilder> domainModelBuilderFactory,
			final ModelBuilderFactory<TaskVersionPersistenceModel,
					TaskVersionPersistenceModel.TaskVersionPersistenceModelBuilder> persistenceModelBuilderFactory)
	{
		this.domainModelBuilderFactory = domainModelBuilderFactory;
		this.persistenceModelBuilderFactory = persistenceModelBuilderFactory;
	}
}