package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag(FAST)
@DisplayName("Task DELETE and OPTIONS Endpoint Tests")
class TaskDELETEITCase extends AbstractTaskITCase
{
	@Test
	@DisplayName("Should delete a task by ID")
	void shouldDeleteTaskById() throws Exception
	{
		TaskAPIModelCreate taskToCreate = TaskAPIModelCreate.of(uniqueTaskTitle("Task to delete"), Optional.empty());
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		mockMvc.perform(delete("/task/delete/{id}", createdTask.id()))
			   .andExpect(status().isNoContent());

		mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
			   .andExpect(status().isNotFound());
	}
}