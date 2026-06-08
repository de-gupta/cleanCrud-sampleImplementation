package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Durable Process Retry Tests")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@TestPropertySource(properties = "clean-crud.process.poll-interval=PT0.05S")
class TaskDurableProcessRetryITCase extends AbstractTaskITCase
{
	@Test
	@Tag(FAST)
	@DisplayName("Should retry a failed durable task through the framework scheduler and eventually patch the task")
	void shouldRetryDurableProcessThroughSchedulerAndEventuallyPatchTask() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Process Task [retry-once]"),
				Optional.of("Task that should be retried once before printing")));

		var updatedTask = waitForTaskTitle(createdTask.id(), createdTask.title() + " [printed]");

		assertThat(updatedTask.title())
				.isEqualTo(createdTask.title() + " [printed]");

		assertThat(updatedTask.description())
				.isEqualTo(createdTask.description());
	}
}
