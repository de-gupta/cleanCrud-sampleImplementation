package de.gupta.clean.crud.implementation.benchmark;

import org.testcontainers.containers.PostgreSQLContainer;

public final class BenchmarkPostgresContainer
{
	private static final PostgreSQLContainer<?> container =
			new PostgreSQLContainer<>(System.getProperty("postgres.docker.image", "postgres:18.3"))
					.withDatabaseName("bench")
					.withUsername("bench")
					.withPassword("bench");

	static
	{
		container.start();

		System.setProperty("spring.datasource.url", container.getJdbcUrl());
		System.setProperty("spring.datasource.username", container.getUsername());
		System.setProperty("spring.datasource.password", container.getPassword());
		System.setProperty("spring.datasource.driver-class-name", container.getDriverClassName());
		System.setProperty("spring.jpa.hibernate.ddl-auto", "update"); // or validate
		System.setProperty("spring.profiles.active", "benchmark");
	}

	public static PostgreSQLContainer<?> getContainer()
	{
		return container;
	}
}