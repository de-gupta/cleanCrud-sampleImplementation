package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.gupta.clean.crud.implementation.examples.setup.IntegrationTest;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@Rollback
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
abstract class AbstractTaskITCase
{
	protected static final Set<String> usedTitles = new HashSet<>();

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	protected static void assertEquality(final TaskAPIModelCreate taskToCreate, final TaskAPIModelResponse createdTask)
	{
		assertThat(createdTask)
				.as("Created task should have the same title as requested")
				.extracting(TaskAPIModelResponse::title)
				.isEqualTo(taskToCreate.title());

		assertThat(createdTask.description())
				.as("Created task should have the same description as requested")
				.isEqualTo(taskToCreate.description());

		assertThat(createdTask.id())
				.as("Created task should have a non-null ID")
				.isNotNull();
	}

	protected static String uniqueTaskTitle(String baseTitle)
	{
		if (baseTitle.contains("Pagination") || baseTitle.contains("Random Task"))
		{
			return baseTitle + " - " + System.currentTimeMillis();
		}

		String uniqueTitle = baseTitle;
		int counter = 1;

		synchronized (usedTitles)
		{
			while (usedTitles.contains(uniqueTitle))
			{
				uniqueTitle = baseTitle + " (" + counter++ + ")";
			}
			usedTitles.add(uniqueTitle);
		}

		return uniqueTitle;
	}

	protected TaskAPIModelResponse createTask(TaskAPIModelCreate taskToCreate) throws Exception
	{
		MvcResult result = mockMvc.perform(post("/task/save")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(objectMapper.writeValueAsString(taskToCreate)))
								  .andExpect(status().isCreated())
								  .andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), TaskAPIModelResponse.class);
	}

	protected TaskAPIModelResponse fetchTask(final Long taskId) throws Exception
	{
		MvcResult result = mockMvc.perform(get("/task/fetch/{id}", taskId))
		                          .andExpect(status().isOk())
		                          .andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), TaskAPIModelResponse.class);
	}
}
