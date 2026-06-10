package de.gupta.clean.crud.implementation.examples.task.useCases.operation.mutation.annotate.domain;

import de.gupta.clean.crud.template.useCases.operation.domain.model.ApplicationOperationPayload;

public record AnnotateTaskMutation(
		String title,
		String note) implements ApplicationOperationPayload
{
}
