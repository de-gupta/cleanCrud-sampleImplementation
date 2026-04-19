package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.standard.SatelliteUpdatePatchItem;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

abstract class AbstractTaskNoteITCase extends AbstractTaskITCase
{
	private static final AtomicLong noteCounter = new AtomicLong(System.currentTimeMillis());

	protected static String nextNoteValue()
	{
		return "note-" + noteCounter.incrementAndGet();
	}

	protected static SatelliteUpdatePatchItem<Long, NoteAPIModelUpdatePatch> notePatchWithId(final long id,
	                                                                                         final String note)
	{
		return SatelliteUpdatePatchItem.of(
				java.util.Optional.of(id),
				NoteAPIModelUpdatePatch.of(java.util.Optional.of(note)));
	}

	protected static SatelliteUpdatePatchItem<Long, NoteAPIModelUpdatePatch> notePatchWithoutId(final String note)
	{
		return SatelliteUpdatePatchItem.of(
				java.util.Optional.empty(),
				NoteAPIModelUpdatePatch.of(java.util.Optional.of(note)));
	}

	protected ResultActions saveTask(final TaskAPIModelCreate taskToCreate) throws Exception
	{
		return mockMvc.perform(post("/task/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(taskToCreate)));
	}

	protected TaskAPIModelResponse fetchTask(final long taskId) throws Exception
	{
		var result = mockMvc.perform(get("/task/fetch/{id}", taskId))
		                    .andExpect(status().isOk())
		                    .andReturn();

		return readTaskResponse(result);
	}

	protected TaskAPIModelResponse patchTask(final long taskId, final TaskAPIModelUpdatePatch patch) throws Exception
	{
		var result = mockMvc.perform(patch("/task/update/{id}", taskId)
									.contentType(MediaType.APPLICATION_JSON)
				                    .content(objectMapper.writeValueAsString(patch)))
		                    .andExpect(status().isOk())
		                    .andReturn();

		return readTaskResponse(result);
	}

	protected NoteAPIModelResponse fetchNote(final long noteId) throws Exception
	{
		var result = mockMvc.perform(get("/note/fetch/{id}", noteId))
		                    .andExpect(status().isOk())
		                    .andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), NoteAPIModelResponse.class);
	}

	protected TaskAPIModelResponse readTaskResponse(final MvcResult mvcResult) throws Exception
	{
		return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TaskAPIModelResponse.class);
	}
}