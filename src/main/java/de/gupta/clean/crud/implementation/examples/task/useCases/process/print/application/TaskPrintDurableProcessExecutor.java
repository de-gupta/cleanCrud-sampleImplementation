package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.AppendPrintedSuffixToTaskTitleCommand;
import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.TaskPrintPayload;
import de.gupta.clean.crud.template.useCases.process.application.execution.DurableProcessExecutionContext;
import de.gupta.clean.crud.template.useCases.process.application.execution.DurableProcessExecutor;
import de.gupta.clean.crud.template.useCases.process.domain.model.outcome.DurableProcessOutcome;
import de.gupta.clean.crud.template.useCases.process.domain.model.outcome.FailureClassification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
final class TaskPrintDurableProcessExecutor implements DurableProcessExecutor<TaskPrintPayload>
{
	@Override
	public DurableProcessOutcome execute(
			final TaskPrintPayload payload,
			final DurableProcessExecutionContext context)
	{
		System.out.printf(
				"Durable task print process: taskId=%s, title=%s, attempt=%s%n",
				payload.taskId(),
				payload.title(),
				context.attemptNumber());
		if (payload.title().contains("[retry-once]") && !context.isRetryAttempt())
		{
			return DurableProcessOutcome.retryAt(
					context.startedAt().plusMillis(100),
					FailureClassification.TRANSIENT_TECHNICAL_FAILURE,
					"Simulated transient print failure");
		}
		return DurableProcessOutcome.succeeded(
				List.of(new AppendPrintedSuffixToTaskTitleCommand(
						payload.taskId(),
						payload.title() + " [printed]")),
				"PRINTED");
	}
}
