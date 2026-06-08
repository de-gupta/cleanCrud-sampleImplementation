package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Durable Process Slow Execution Tests")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TaskDurableProcessSlowITCase extends AbstractTaskITCase
{
	@Test
	@Tag(FAST)
	@DisplayName("Should keep task creation responsive while a slow durable process patches the task later")
	void shouldPatchTaskLaterWhenDurableProcessExecutionIsSlow() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Process Task [slow]"),
				Optional.of("Task that should be patched after a slow durable process")));

		var fetchedImmediately = fetchTask(createdTask.id());
		var updatedTask = waitForTaskTitle(createdTask.id(), createdTask.title() + " [printed]");

		assertThat(fetchedImmediately.title())
				.isEqualTo(createdTask.title());

		assertThat(updatedTask.title())
				.isEqualTo(createdTask.title() + " [printed]");

		assertThat(updatedTask.description())
				.isEqualTo(createdTask.description());
	}
}
