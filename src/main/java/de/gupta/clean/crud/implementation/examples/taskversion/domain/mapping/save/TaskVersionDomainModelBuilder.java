package de.gupta.clean.crud.implementation.examples.taskversion.domain.mapping.save;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDomainModelBuilder
		implements DomainModelBuilder<TaskVersionDomainModelCreate, TaskVersionDomainModel>
{
	private final ModelBuilderFactory<TaskVersionDomainModel, TaskVersionDomainModel.TaskVersionDomainModelBuilder>
			modelBuilderFactory;

	@Override
	public TaskVersionDomainModel toModel(final TaskVersionDomainModelCreate domainModelCreate)
	{
		return modelBuilderFactory.builder()
		                          .withVersion(domainModelCreate.version())
		                          .build();
	}

	TaskVersionDomainModelBuilder(
			final ModelBuilderFactory<TaskVersionDomainModel, TaskVersionDomainModel.TaskVersionDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}