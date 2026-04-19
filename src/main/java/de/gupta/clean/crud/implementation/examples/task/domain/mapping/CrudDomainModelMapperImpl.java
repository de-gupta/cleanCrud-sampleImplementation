package de.gupta.clean.crud.implementation.examples.task.domain.mapping;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.CrudDomainModelMapper;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class CrudDomainModelMapperImpl implements
		CrudDomainModelMapper<TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse>
{
	private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder>
			modelBuilderFactory;

	@Override
	public TaskDomainModelResponse toResponse(final TaskDomainModel taskDomainModel)
	{
		return new TaskDomainModelResponse(
				taskDomainModel.title(),
				taskDomainModel.description(),
				taskDomainModel.versions(),
				taskDomainModel.notes());
	}

	@Override
	public TaskDomainModel toModel(final TaskDomainModelCreate taskDomainModelCreate)
	{
		return modelBuilderFactory.builder()
								  .withTitle(taskDomainModelCreate.title())
								  .withDescription(taskDomainModelCreate.description())
								  .withVersions(java.util.List.of())
								  .withNotes(java.util.List.of())
								  .build();
	}

	@Override
	public TaskDomainModel patchModel(final TaskDomainModel originalModel,
									  final TaskDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
								  .withTitle(updatePatch.title().orElse(originalModel.title()))
								  .withDescription(updatePatch.description().isPresent() ?
										  updatePatch.description() :
										  originalModel.description())
								  .withVersions(originalModel.versions())
								  .withNotes(originalModel.notes())
								  .build();
	}

	CrudDomainModelMapperImpl(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}
