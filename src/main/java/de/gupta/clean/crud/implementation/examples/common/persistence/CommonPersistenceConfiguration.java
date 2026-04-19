package de.gupta.clean.crud.implementation.examples.common.persistence;

import de.gupta.clean.crud.template.infrastructure.persistence.transaction.PersistenceTransactionRunner;
import de.gupta.clean.crud.template.infrastructure.persistence.transaction.SpringPersistenceTransactionRunner;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.AggregateLifecycleEngine;
import de.gupta.clean.crud.template.useCases.crud.aggregate.engine.DefaultAggregateLifecycleEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CommonPersistenceConfiguration
{
	@Bean
	public PersistenceTransactionRunner persistenceTransactionRunner(
			final PlatformTransactionManager transactionManager)
	{
		return SpringPersistenceTransactionRunner.withTransactionManager(transactionManager);
	}

	@Bean
	public AggregateLifecycleEngine aggregateLifecycleEngine(
			final PersistenceTransactionRunner persistenceTransactionRunner)
	{
		return DefaultAggregateLifecycleEngine.withTransactionRunner(persistenceTransactionRunner);
	}
}
