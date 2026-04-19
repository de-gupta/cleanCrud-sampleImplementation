package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

abstract class AbstractTaskVersionITCase extends AbstractTaskITCase
{
	private static final AtomicLong versionCounter = new AtomicLong(System.currentTimeMillis());

	protected static long nextVersionValue()
	{
		return versionCounter.incrementAndGet();
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

	protected VersionAPIModelResponse fetchVersion(final long versionId) throws Exception
	{
		var result = mockMvc.perform(get("/version/fetch/{id}", versionId))
		                    .andExpect(status().isOk())
		                    .andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), VersionAPIModelResponse.class);
	}

	protected TaskAPIModelResponse readTaskResponse(final MvcResult mvcResult) throws Exception
	{
		return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TaskAPIModelResponse.class);
	}
}