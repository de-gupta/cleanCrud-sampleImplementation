package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain;

import de.gupta.clean.crud.template.useCases.process.domain.definition.DurableProcessTrigger;

public record TaskCreatedPrintTrigger(Long taskId) implements DurableProcessTrigger
{
}
