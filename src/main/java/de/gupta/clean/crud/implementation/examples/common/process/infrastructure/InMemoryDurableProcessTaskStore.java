package de.gupta.clean.crud.implementation.examples.common.process.infrastructure;

import de.gupta.clean.crud.template.useCases.process.domain.model.id.DurableProcessTaskId;
import de.gupta.clean.crud.template.useCases.process.domain.model.task.DurableProcessTask;
import de.gupta.clean.crud.template.useCases.process.port.persistence.DurableProcessTaskRepository;
import de.gupta.clean.crud.template.useCases.process.port.scheduling.DurableProcessTaskScheduler;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryDurableProcessTaskStore
		implements DurableProcessTaskRepository, DurableProcessTaskScheduler
{
	private final Map<DurableProcessTaskId, DurableProcessTask> tasks = new ConcurrentHashMap<>();

	@Override
	public DurableProcessTask save(final DurableProcessTask task)
	{
		tasks.put(task.taskId(), task);
		return task;
	}

	@Override
	public DurableProcessTask update(final DurableProcessTask task)
	{
		tasks.put(task.taskId(), task);
		return task;
	}

	@Override
	public Optional<DurableProcessTask> findById(final DurableProcessTaskId taskId)
	{
		return Optional.ofNullable(tasks.get(taskId));
	}

	@Override
	public Collection<DurableProcessTask> findDueTasks(final Instant asOf, final int limit)
	{
		return tasks.values()
		            .stream()
		            .filter(task -> task.isDueAt(asOf))
		            .sorted(Comparator
							.comparing((DurableProcessTask task) -> task.nextAttemptAt()
				                                                        .orElse(task.createdAt()))
				            .thenComparing(DurableProcessTask::createdAt))
		            .limit(limit)
		            .toList();
	}

	@Override
	public DurableProcessTask scheduleRetry(final DurableProcessTask task, final Instant nextAttemptAt)
	{
		tasks.put(task.taskId(), task);
		return task;
	}
}