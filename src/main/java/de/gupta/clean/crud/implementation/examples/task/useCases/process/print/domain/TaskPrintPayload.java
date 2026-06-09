package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain;

import de.gupta.clean.crud.template.useCases.process.domain.definition.DurableProcessPayload;

public record TaskPrintPayload(
		Long taskId,
		String title)
		implements DurableProcessPayload
{
}
