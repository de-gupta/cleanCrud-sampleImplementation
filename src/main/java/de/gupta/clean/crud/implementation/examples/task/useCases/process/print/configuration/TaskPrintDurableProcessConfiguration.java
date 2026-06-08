package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.configuration;

import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.TaskCreatedPrintTrigger;
import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.TaskPrintPayload;
import de.gupta.clean.crud.template.useCases.process.application.execution.DurableProcessExecutor;
import de.gupta.clean.crud.template.useCases.process.application.registration.DurableRegisteredProcess;
import de.gupta.clean.crud.template.useCases.process.domain.definition.DurableProcessDefinition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TaskPrintDurableProcessConfiguration
{
	static final String TASK_PRINT_PROCESS_TYPE = "task.print.and.patch.title";

	@Bean
	@Qualifier("taskPrintProcessDefinition")
	DurableProcessDefinition<TaskCreatedPrintTrigger, TaskPrintPayload> taskPrintProcessDefinition()
	{
		return DurableProcessDefinition.of(
				TASK_PRINT_PROCESS_TYPE,
				TaskCreatedPrintTrigger.class,
				TaskPrintPayload.class);
	}

	@Bean
	DurableRegisteredProcess<TaskCreatedPrintTrigger, TaskPrintPayload> taskPrintRegisteredProcess(
			@Qualifier("taskPrintProcessDefinition") final DurableProcessDefinition<TaskCreatedPrintTrigger, TaskPrintPayload> definition,
			final DurableProcessExecutor<TaskPrintPayload> executor)
	{
		return new DurableRegisteredProcess<>(definition, executor);
	}
}