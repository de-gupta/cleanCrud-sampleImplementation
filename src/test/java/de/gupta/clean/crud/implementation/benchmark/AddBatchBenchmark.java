package de.gupta.clean.crud.implementation.benchmark;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class AddBatchBenchmark
{
	private final List<Integer> batchSizes = List.of(10, 80, 500, 1_000, 5_000, 10_000, 20_000);
	private DomainPersistenceIDManagement<Long, UUID> idManagement;
	private List<List<UUID>> batches;
	@Param({"0", "1", "2", "3", "4", "5", "6"})
	private int batchIndex;

	public static void main(String[] args) throws RunnerException
	{
		Options options = new OptionsBuilder()
				.include(AddBatchBenchmark.class.getSimpleName())
				.build();
		new Runner(options).run();
	}

	@Setup(Level.Trial)
	public void setup()
	{
		BenchmarkPostgresContainer.getContainer();
		final var builder = new SpringApplicationBuilder(BenchmarkApplication.class);
		builder.web(WebApplicationType.NONE);
		builder.profiles("benchmark");
		ConfigurableApplicationContext context = builder.run();

		idManagement = context.getBean(DomainPersistenceIDManagement.class);

		batches = new ArrayList<>();
		for (Integer size : batchSizes)
		{
			batches.add(generateTestData(size));
		}
	}

	private List<UUID> generateTestData(int size)
	{
		List<UUID> data = new ArrayList<>(size);
		for (long i = 1; i <= size; i++)
		{
			data.add(UUID.randomUUID());
		}
		return data;
	}

	@Benchmark
	public void benchmarkAddBatch(Blackhole blackhole)
	{
		var result = idManagement.addBatch(batches.get(batchIndex));
		blackhole.consume(result);
	}
}