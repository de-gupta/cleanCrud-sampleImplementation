package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.setup.TestTags;
import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.mutation.annotate.domain.AnnotateTaskMutation;
import de.gupta.clean.crud.template.useCases.mutation.api.application.MutationApplicationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Mutation Tests")
class TaskMutationITCase extends AbstractTaskITCase
{
	@Autowired
	@Qualifier("taskMutationApplicationController")
	private MutationApplicationController<Long, TaskDomainModel> taskMutationApplicationController;

	@Test
	@Tag(TestTags.FAST)
	@DisplayName("Should update task title and append an owned note in one internal mutation")
	void shouldUpdateTaskTitleAndAppendOwnedNoteInOneInternalMutation() throws Exception
	{
		var createdTask = createTask(new TaskAPIModelCreate(
				uniqueTaskTitle("task-mutation"),
				Optional.of("before"),
				java.util.List.of(),
				java.util.List.of()));
		taskMutationApplicationController.applyInternalCommand(
				createdTask.id(),
				new AnnotateTaskMutation(uniqueTaskTitle("task-annotated"), "added from mutation"));
		var fetched = fetchTask(createdTask.id());

		assertThat(fetched.title()).startsWith("task-annotated");
		assertThat(fetched.notes()).hasSize(1);
		assertThat(fetched.notes().stream().findFirst().orElseThrow().note()).isEqualTo("added from mutation");
	}
}
