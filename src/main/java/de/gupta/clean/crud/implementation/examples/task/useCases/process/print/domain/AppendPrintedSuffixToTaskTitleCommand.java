package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain;

import de.gupta.clean.crud.template.useCases.process.domain.action.ApplicationCommand;

public record AppendPrintedSuffixToTaskTitleCommand(
		Long taskId,
		String updatedTitle)
		implements ApplicationCommand
{
}
