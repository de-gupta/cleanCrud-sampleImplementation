package de.gupta.clean.crud.implementation.examples.task.domain.mapping.update;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainModelPatcher implements DomainModelPatcher<TaskDomainModel, TaskDomainModelUpdatePatch>
{
	private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> modelBuilderFactory;

	@Override
	public TaskDomainModel patchModel(final TaskDomainModel originalModel,
									  final TaskDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
								  .withTitle(updatePatch.title().orElse(originalModel.title()))
								  .withDescription(updatePatch.description().isPresent() ?
										  updatePatch.description() :
										  originalModel.description())
								  .build();
	}

	TaskDomainModelPatcher(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}