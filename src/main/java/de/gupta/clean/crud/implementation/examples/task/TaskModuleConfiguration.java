package de.gupta.clean.crud.implementation.examples.task;

import de.gupta.clean.crud.template.infrastructure.persistence.transaction.PersistenceTransactionRunner;
import de.gupta.clean.crud.template.infrastructure.persistence.transaction.SpringPersistenceTransactionRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class TaskModuleConfiguration
{
	@Bean
	public PersistenceTransactionRunner persistenceTransactionRunner(
			final PlatformTransactionManager transactionManager)
	{
		return SpringPersistenceTransactionRunner.withTransactionManager(transactionManager);
	}
}