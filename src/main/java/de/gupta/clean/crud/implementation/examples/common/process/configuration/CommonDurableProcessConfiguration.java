package de.gupta.clean.crud.implementation.examples.common.process.configuration;

import de.gupta.clean.crud.template.useCases.process.application.dispatch.ApplicationActionDispatcher;
import de.gupta.clean.crud.template.useCases.process.application.execution.DefaultDurableProcessRunner;
import de.gupta.clean.crud.template.useCases.process.application.execution.DurableProcessExecutionNudge;
import de.gupta.clean.crud.template.useCases.process.application.execution.DurableProcessRunner;
import de.gupta.clean.crud.template.useCases.process.application.execution.ImmediateDurableProcessExecutionNudge;
import de.gupta.clean.crud.template.useCases.process.application.registration.*;
import de.gupta.clean.crud.template.useCases.process.infrastructure.persistence.InMemoryDurableProcessTaskStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.Collection;

@Configuration
public class CommonDurableProcessConfiguration
{
	@Bean
	public Clock durableProcessClock()
	{
		return Clock.systemUTC();
	}

	@Bean
	public InMemoryDurableProcessTaskStore inMemoryDurableProcessTaskStore()
	{
		return InMemoryDurableProcessTaskStore.create();
	}

	@Bean
	public DurableProcessDefinitionRegistry durableProcessDefinitionRegistry(
			final Collection<DurableRegisteredProcess<?, ?>> registeredProcesses)
	{
		return DefaultDurableProcessDefinitionRegistry.of(registeredProcesses);
	}

	@Bean
	public DurableProcessStarter durableProcessStarter(
			final InMemoryDurableProcessTaskStore taskStore,
			final Clock durableProcessClock)
	{
		return DefaultDurableProcessStarter.with(taskStore, durableProcessClock);
	}

	@Bean
	public DurableProcessRunner durableProcessRunner(
			final DurableProcessDefinitionRegistry definitionRegistry,
			final InMemoryDurableProcessTaskStore taskStore,
			final ApplicationActionDispatcher applicationActionDispatcher,
			final Clock durableProcessClock)
	{
		return DefaultDurableProcessRunner.with(
				definitionRegistry,
				taskStore,
				taskStore,
				applicationActionDispatcher,
				durableProcessClock);
	}

	@Bean
	public DurableProcessExecutionNudge durableProcessExecutionNudge(
			final DurableProcessRunner durableProcessRunner,
			final Clock durableProcessClock)
	{
		return ImmediateDurableProcessExecutionNudge.with(durableProcessRunner, durableProcessClock);
	}
}