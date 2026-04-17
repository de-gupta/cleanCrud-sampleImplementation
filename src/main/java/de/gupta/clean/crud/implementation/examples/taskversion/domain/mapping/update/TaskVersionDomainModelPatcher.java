package de.gupta.clean.crud.implementation.examples.taskversion.domain.mapping.update;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDomainModelPatcher
		implements DomainModelPatcher<TaskVersionDomainModel, TaskVersionDomainModelUpdatePatch>
{
	private final ModelBuilderFactory<TaskVersionDomainModel, TaskVersionDomainModel.TaskVersionDomainModelBuilder>
			modelBuilderFactory;

	@Override
	public TaskVersionDomainModel patchModel(final TaskVersionDomainModel originalModel,
	                                         final TaskVersionDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
		                          .withVersion(updatePatch.version().orElse(originalModel.version()))

		                          .build();
	}

	TaskVersionDomainModelPatcher(
			final ModelBuilderFactory<TaskVersionDomainModel, TaskVersionDomainModel.TaskVersionDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}