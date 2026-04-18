package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task DELETE Note Relationship Endpoint Tests")
class TaskDELETENoteITCase extends AbstractTaskNoteITCase
{
	@Test
	@DisplayName("Should cascade-delete all linked notes when deleting the task")
	void shouldCascadeDeleteAllLinkedNotesWhenDeletingTheTask() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Delete Notes"),
				Optional.of("Task delete notes description"),
				List.of(),
				List.of(
						new NoteAPIModelCreate(nextNoteValue()),
						new NoteAPIModelCreate(nextNoteValue()))));

		mockMvc.perform(delete("/task/delete/{id}", createdTask.id())
					   .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isNoContent());

		mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
		       .andExpect(status().isNotFound());

		for (var note : createdTask.notes())
		{
			mockMvc.perform(get("/note/fetch/{id}", note.id()))
			       .andExpect(status().isNotFound());
		}
	}
}