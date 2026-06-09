package de.gupta.clean.crud.implementation.examples.task.useCases.mutation.annotate.domain;

import de.gupta.clean.crud.template.useCases.mutation.domain.model.ApplicationMutationPayload;

public record AnnotateTaskMutation(
		String title,
		String note) implements ApplicationMutationPayload
{
}
