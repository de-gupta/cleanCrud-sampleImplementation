package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Durable Process Tests")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class TaskDurableProcessITCase extends AbstractTaskITCase
{
	@Test
	@Tag(FAST)
	@DisplayName("Should print a created task and patch its title through the application API")
	void shouldPrintTaskAndPatchTitleThroughApplicationApi() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Process Task"),
				Optional.of("Task that should be printed")));

		var updatedTask = waitForTaskTitle(createdTask.id(), createdTask.title() + " [printed]");

		assertThat(updatedTask.title())
				.isEqualTo(createdTask.title() + " [printed]");

		assertThat(updatedTask.description())
				.isEqualTo(createdTask.description());
	}

	private TaskAPIModelResponse waitForTaskTitle(final Long taskId, final String expectedTitle) throws Exception
	{
		var deadline = System.nanoTime() + java.util.concurrent.TimeUnit.SECONDS.toNanos(5);
		TaskAPIModelResponse lastSeen = null;

		while (System.nanoTime() < deadline)
		{
			lastSeen = fetchTask(taskId);
			if (expectedTitle.equals(lastSeen.title()))
			{
				return lastSeen;
			}
			Thread.sleep(50);
		}

		assertThat(lastSeen).isNotNull();
		assertThat(lastSeen.title()).isEqualTo(expectedTitle);
		return lastSeen;
	}
}
