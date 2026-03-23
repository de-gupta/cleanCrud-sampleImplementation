package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task GET Endpoint Tests")
class TaskGETITCase extends AbstractTaskITCase
{
	@Test
	@DisplayName("Should retrieve a task by ID")
	void shouldRetrieveTaskById() throws Exception
	{
		TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate("Task to retrieve",
				Optional.of("This task will be retrieved by ID"));
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		var result = mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
							.andExpect(status().isOk())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse retrievedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(retrievedTask)
				.as("Retrieved task should match the created task")
				.usingRecursiveComparison()
				.isEqualTo(createdTask);
	}

	@Test
	@DisplayName("Should retrieve all tasks with pagination")
	void shouldRetrieveAllTasks() throws Exception
	{
		int taskCount = 250;
		List<TaskAPIModelResponse> createdTasks = new ArrayList<>();

		for (int i = 0; i < taskCount; i++)
		{
			TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate(
					uniqueTaskTitle("Pagination Task " + i),
					Optional.of("Description for pagination task " + i)
			);
			createdTasks.add(createTask(taskToCreate));
		}

		int[] pageSizes = {10, 25, 50, 100};

		for (int pageSize : pageSizes)
		{
			ResultActions firstPageResult = mockMvc.perform(get("/task/fetch")
					.param("page", "0")
					.param("size", String.valueOf(pageSize)));

			firstPageResult
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").isArray())
					.andExpect(jsonPath("$.content.length()").value(pageSize))
					.andExpect(jsonPath("$.numberOfElements").value(pageSize));

			ResultActions secondPageResult = mockMvc.perform(get("/task/fetch")
					.param("page", "1")
					.param("size", String.valueOf(pageSize)));

			secondPageResult
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").isArray())
					.andExpect(jsonPath("$.content.length()").value(greaterThanOrEqualTo(1)));
		}
	}

	@Test
	@DisplayName("Should retrieve all tasks with pagination and filtering")
	void shouldRetrieveAllTasksWithPagination() throws Exception
	{
		String filterKeyword = "FILTERABLE";
		int filteredTaskCount = 200;
		int regularTaskCount = 100;

		for (int i = 0; i < filteredTaskCount; i++)
		{
			createTask(new TaskAPIModelCreate(
					uniqueTaskTitle(filterKeyword + " Task " + i),
					Optional.of("Description for filterable task " + i)
			));
		}

		for (int i = 0; i < regularTaskCount; i++)
		{
			createTask(new TaskAPIModelCreate(
					uniqueTaskTitle("Regular Task " + i),
					Optional.of("Description for regular task " + i)
			));
		}

		System.out.println(
				"Created " + (filteredTaskCount + regularTaskCount) + " tasks for pagination and filtering testing");

		int[] pageSizes = {10, 25, 50, 100};

		for (int pageSize : pageSizes)
		{
			ResultActions firstPageResult = mockMvc.perform(get("/task/fetch")
					.param("page", "0")
					.param("size", String.valueOf(pageSize)));

			firstPageResult
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").isArray())
					.andExpect(jsonPath("$.content.length()").value(pageSize))
					.andExpect(jsonPath("$.numberOfElements").value(pageSize));

			int lastPageIndex = ((filteredTaskCount + regularTaskCount) - 1) / pageSize;
			ResultActions lastPageResult = mockMvc.perform(get("/task/fetch")
					.param("page", String.valueOf(lastPageIndex))
					.param("size", String.valueOf(pageSize)));

			lastPageResult
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content").isArray())
					.andExpect(jsonPath("$.numberOfElements").value(greaterThanOrEqualTo(0)));
		}
	}

	@Test
	@DisplayName("Should return 404 when task not found")
	void shouldReturn404WhenTaskNotFound() throws Exception
	{
		mockMvc.perform(get("/task/fetch/{id}", 999999L))
			   .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Should handle malformed ID in URL path")
	void shouldHandleMalformedIdInPath() throws Exception
	{
		mockMvc.perform(get("/task/fetch/{id}", "not-a-number"))
			   .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Should retrieve multiple tasks by IDs")
	void shouldRetrieveTasksByIds() throws Exception
	{
		// Create multiple tasks
		int taskCount = 5;
		List<TaskAPIModelResponse> createdTasks = new ArrayList<>();

		for (int i = 0; i < taskCount; i++)
		{
			TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate(
					uniqueTaskTitle("Batch Fetch Task " + i),
					Optional.of("Description for batch fetch task " + i)
			);
			createdTasks.add(createTask(taskToCreate));
		}

		// Extract IDs from created tasks
		Set<Long> taskIds = createdTasks.stream()
										.map(TaskAPIModelResponse::id)
										.collect(Collectors.toSet());

		// Create request with IDs as parameters
		var requestBuilder = get("/task/fetch/ids");

		// Add each ID as a separate 'ids' parameter
		for (Long id : taskIds)
		{
			requestBuilder = requestBuilder.param("ids", id.toString());
		}

		// Perform request to fetch tasks by IDs
		var result = mockMvc.perform(requestBuilder
									.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.*", hasSize(taskCount)))
							.andReturn();

		// Parse response
		String responseContent = result.getResponse().getContentAsString();
		Map<String, TaskAPIModelResponse> retrievedTasks = objectMapper.readValue(responseContent,
				objectMapper.getTypeFactory()
							.constructMapType(HashMap.class, String.class, TaskAPIModelResponse.class));

		// Verify all tasks were retrieved correctly
		assertThat(retrievedTasks).hasSize(taskCount);

		for (TaskAPIModelResponse createdTask : createdTasks)
		{
			TaskAPIModelResponse retrievedTask = retrievedTasks.get(createdTask.id().toString());
			assertThat(retrievedTask)
					.as("Retrieved task should match the created task")
					.usingRecursiveComparison()
					.isEqualTo(createdTask);
		}
	}

	@Test
	@DisplayName("Should handle mix of existing and non-existent IDs")
	void shouldHandleMixOfExistingAndNonExistentIds() throws Exception
	{
		// Create a task
		TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate(
				uniqueTaskTitle("Existing Task"),
				Optional.of("This task exists")
		);
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		// Create a set with both existing and non-existent IDs
		Set<Long> mixedIds = Set.of(createdTask.id(), 99999L, 99998L);

		// Create request with IDs as parameters
		var requestBuilder = get("/task/fetch/ids");

		// Add each ID as a separate 'ids' parameter
		for (Long id : mixedIds)
		{
			requestBuilder = requestBuilder.param("ids", id.toString());
		}

		// Perform request to fetch tasks by IDs
		var result = mockMvc.perform(requestBuilder
									.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andReturn();

		// Parse response
		String responseContent = result.getResponse().getContentAsString();
		Map<String, TaskAPIModelResponse> retrievedTasks = objectMapper.readValue(responseContent,
				objectMapper.getTypeFactory()
							.constructMapType(HashMap.class, String.class, TaskAPIModelResponse.class));

		// Verify only the existing task was retrieved
		assertThat(retrievedTasks).hasSize(1);

		TaskAPIModelResponse retrievedTask = retrievedTasks.get(createdTask.id().toString());
		assertThat(retrievedTask)
				.as("Retrieved task should match the created task")
				.usingRecursiveComparison()
				.isEqualTo(createdTask);
	}

	@Test
	@DisplayName("Should handle empty list of IDs")
	void shouldHandleEmptyListOfIds() throws Exception
	{
		// Perform request with empty set of IDs
		var requestBuilder = get("/task/fetch/ids");

		// Explicitly pass an empty parameter to ensure the endpoint is hit
		requestBuilder = requestBuilder.param("ids", "");

		var result = mockMvc.perform(requestBuilder
									.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andReturn();

		// Parse response
		String responseContent = result.getResponse().getContentAsString();
		Map<String, TaskAPIModelResponse> retrievedTasks = objectMapper.readValue(responseContent,
				objectMapper.getTypeFactory()
							.constructMapType(HashMap.class, String.class, TaskAPIModelResponse.class));

		// Verify empty map was returned
		assertThat(retrievedTasks).isEmpty();
	}

	@Test
	@DisplayName("Should handle invalid IDs")
	void shouldHandleInvalidIds() throws Exception
	{
		// Perform request with invalid IDs
		mockMvc.perform(get("/task/fetch/ids")
					   .param("ids", "not-a-number")
					   .param("ids", "another-invalid-id")
					   .contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isBadRequest());
	}
}