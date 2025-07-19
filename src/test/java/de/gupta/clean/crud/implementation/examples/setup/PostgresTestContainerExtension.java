package de.gupta.clean.crud.implementation.examples.setup;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
final class PostgresTestContainerExtension implements BeforeAllCallback
{
	@Container
	private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
			new PostgreSQLContainer<>("postgres:17.5")
					.withDatabaseName("test")
					.withUsername("test")
					.withPassword("test");

	static
	{
		POSTGRE_SQL_CONTAINER.start();

		System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
		System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
		System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
	}

	@Override
	public void beforeAll(ExtensionContext context)
	{
		// If you need more logic before all tests, do it here
	}
}