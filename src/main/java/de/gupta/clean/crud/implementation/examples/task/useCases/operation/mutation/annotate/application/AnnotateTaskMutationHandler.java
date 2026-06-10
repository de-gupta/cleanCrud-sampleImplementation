package de.gupta.clean.crud.implementation.examples.task.useCases.operation.mutation.annotate.application;

import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.useCases.operation.mutation.annotate.domain.AnnotateTaskMutation;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.useCases.crud.aggregate.intent.SatelliteMutationIntent;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.AggregateMutationHandler;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.plan.AggregateMutationPlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
final class AnnotateTaskMutationHandler implements AggregateMutationHandler<TaskDomainModel, AnnotateTaskMutation>
{
	private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder>
			taskDomainModelBuilderFactory;

	@Override
	public AggregateMutationPlan<TaskDomainModel> apply(
			final TaskDomainModel currentModel,
			final AnnotateTaskMutation payload)
	{
		var updatedRoot = taskDomainModelBuilderFactory.builder()
		                                               .withTitle(payload.title())
		                                               .withDescription(currentModel.description())
		                                               .withVersions(currentModel.versions())
		                                               .withNotes(currentModel.notes())
		                                               .build();
		return AggregateMutationPlan.builder(updatedRoot)
		                            .mutateRelationship(
											"note",
											List.of(new SatelliteMutationIntent.CreateSatelliteMutationIntent<>(
													NoteDomainModelCreate.of(payload.note()))))
		                            .build();
	}

	AnnotateTaskMutationHandler(
			final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> taskDomainModelBuilderFactory)
	{
		this.taskDomainModelBuilderFactory = taskDomainModelBuilderFactory;
	}
}