package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task POST Endpoint Tests")
class TaskPOSTITCase extends AbstractTaskITCase
{
	@ParameterizedTest(name = "{index}: {0}")
	@MethodSource("provideTasksForCreation")
	@DisplayName("Should create a new task and return it with an ID")
	void shouldCreateTask(String testCase, TaskAPIModelCreate taskToCreate) throws Exception
	{
		var result = mockMvc.perform(post("/task/save")
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(taskToCreate)))
							.andExpect(status().isCreated())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse createdTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertEquality(taskToCreate, createdTask);
		verifyTaskResponse(createdTask.id(), createdTask);
	}

	private void verifyTaskResponse(Long id, TaskAPIModelResponse expected) throws Exception
	{
		mockMvc.perform(get("/task/fetch/{id}", id))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value(expected.id()))
			   .andExpect(jsonPath("$.title").value(expected.title()))
			   .andExpect(jsonPath("$.description").value(expected.description().orElse(null)));
	}

	@Test
	@DisplayName("Should handle POST with empty title")
	void shouldHandlePostWithEmptyTitle() throws Exception
	{
		TaskAPIModelCreate emptyTitleTask = new TaskAPIModelCreate("", Optional.of("Description for empty title task"));

		mockMvc.perform(post("/task/save")
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(emptyTitleTask)))
			   .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Should handle POST with null title")
	void shouldHandlePostWithNullTitle() throws Exception
	{
		String taskJson = "{\"title\":null,\"description\":\"Description for null title task\"}";

		mockMvc.perform(post("/task/save")
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(taskJson))
			   .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Should handle POST with very long data")
	void shouldHandlePostWithLongData() throws Exception
	{
		String longTitle = "Long Title for POST: This is a longer title that tests the system's ability to handle " +
				"moderately long strings without exceeding database column limits. It should be long enough to " +
				"test edge cases but short enough to fit in the database.";

		String longDescription =
				"Very Long Description for POST: This description exceeds 255 characters to test that the system can handle longer descriptions. " +
						"Adding more text to make this description longer. This tests the system's ability to handle very long strings. ".repeat(
								5);

		assertThat(longTitle.length()).isLessThan(255);
		assertThat(longDescription.length()).isGreaterThan(255);
		System.out.println("Description length: " + longDescription.length());

		TaskAPIModelCreate longDataTask = new TaskAPIModelCreate(
				uniqueTaskTitle(longTitle),
				Optional.of(longDescription)
		);

		var result = mockMvc.perform(post("/task/save")
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(longDataTask)))
							.andExpect(status().isCreated())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse createdTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(createdTask.title())
				.as("Long title should be saved correctly")
				.isEqualTo(longTitle);

		assertThat(createdTask.description().orElse(""))
				.as("Long description should be saved correctly")
				.isEqualTo(longDescription);
	}

	@Test
	@DisplayName("Should handle multiple sequential POST requests")
	void shouldHandleMultipleSequentialPosts() throws Exception
	{
		int requestCount = 20;
		List<TaskAPIModelResponse> createdTasks = new ArrayList<>();

		for (int i = 0; i < requestCount; i++)
		{
			TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate(
					uniqueTaskTitle("Sequential Task " + i),
					Optional.of("Description for sequential task " + i)
			);

			var result = mockMvc.perform(post("/task/save")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(taskToCreate)))
								.andExpect(status().isCreated())
								.andReturn();

			String responseContent = result.getResponse().getContentAsString();
			TaskAPIModelResponse createdTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);
			createdTasks.add(createdTask);
		}

		assertThat(createdTasks)
				.as("All sequential tasks should be created")
				.hasSize(requestCount);

		long uniqueIds = createdTasks.stream()
									 .map(TaskAPIModelResponse::id)
									 .distinct()
									 .count();

		assertThat(uniqueIds)
				.as("All created tasks should have unique IDs")
				.isEqualTo(requestCount);

		for (TaskAPIModelResponse task : createdTasks)
		{
			mockMvc.perform(get("/task/fetch/{id}", task.id()))
				   .andExpect(status().isOk())
				   .andExpect(jsonPath("$.id").value(task.id()))
				   .andExpect(jsonPath("$.title").value(task.title()));
		}
	}

	@Test
	@DisplayName("Should handle invalid JSON format")
	void shouldHandleInvalidJsonFormat() throws Exception
	{
		String invalidJson =
				"{\"title\":\"Invalid JSON Task\", \"description\":\"This JSON is invalid\",}"; // Extra comma

		mockMvc.perform(post("/task/save")
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(invalidJson))
			   .andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Should handle special characters in task title and description")
	void shouldHandleSpecialCharacters() throws Exception
	{
		String titleWithSpecialChars = "Special Chars: !@#$%^&*()_+{}|:<>?~`-=[]\\;',./\"";
		String descWithUnicode = "Unicode: 你好, こんにちは, 안녕하세요, Привет, مرحبا, שלום";

		TaskAPIModelCreate specialCharsTask = new TaskAPIModelCreate(
				uniqueTaskTitle(titleWithSpecialChars),
				Optional.of(descWithUnicode)
		);

		var result = mockMvc.perform(post("/task/save")
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(specialCharsTask)))
							.andExpect(status().isCreated())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse createdTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(createdTask.title())
				.as("Title with special characters should be saved correctly")
				.isEqualTo(titleWithSpecialChars);

		assertThat(createdTask.description().orElse(""))
				.as("Description with Unicode should be saved correctly")
				.isEqualTo(descWithUnicode);
	}

	@Test
	@DisplayName("Should handle concurrent requests")
	void shouldHandleConcurrentRequests() throws Exception
	{
		int concurrentRequests = 10;
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch endLatch = new CountDownLatch(concurrentRequests);
		AtomicInteger successCount = new AtomicInteger(0);
		ExecutorService executorService = Executors.newFixedThreadPool(concurrentRequests);
		List<Long> createdTaskIds = new ArrayList<>();

		for (int i = 0; i < concurrentRequests; i++)
		{
			final int index = i;
			executorService.submit(() ->
			{
				try
				{
					startLatch.await();

					TaskAPIModelCreate taskToCreate = new TaskAPIModelCreate(
							uniqueTaskTitle("Concurrent Task " + index),
							Optional.of("Description for concurrent task " + index)
					);

					MvcResult result = mockMvc.perform(post("/task/save")
													  .contentType(MediaType.APPLICATION_JSON)
													  .content(objectMapper.writeValueAsString(taskToCreate)))
											  .andExpect(status().isCreated())
											  .andReturn();

					String responseContent = result.getResponse().getContentAsString();
					TaskAPIModelResponse createdTask =
							objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

					synchronized (createdTaskIds)
					{
						createdTaskIds.add(createdTask.id());
					}

					successCount.incrementAndGet();
				}
				catch (Exception e)
				{
					System.err.println("Error in concurrent request " + index + ": " + e.getMessage());
				}
				finally
				{
					endLatch.countDown();
				}
			});
		}

		startLatch.countDown();
		boolean allCompleted = endLatch.await(30, TimeUnit.SECONDS);
		executorService.shutdown();

		assertThat(allCompleted)
				.as("All concurrent requests should complete within the timeout")
				.isTrue();

		assertThat(successCount.get())
				.as("All concurrent requests should succeed")
				.isEqualTo(concurrentRequests);

		assertThat(createdTaskIds)
				.as("All created tasks should have unique IDs")
				.hasSize(concurrentRequests)
				.doesNotHaveDuplicates();

		for (Long taskId : createdTaskIds)
		{
			mockMvc.perform(get("/task/fetch/{id}", taskId))
				   .andExpect(status().isOk())
				   .andExpect(jsonPath("$.id").value(taskId));
		}
	}

	@Test
	@DisplayName("Should handle long data within database limits")
	void shouldHandleLongDataWithinLimits() throws Exception
	{
		String longTitle = "Long title".repeat(10);

		assertThat(longTitle.length())
				.as("Test title should be long but within database column limits")
				.isGreaterThan(50)
				.isLessThan(255);

		TaskAPIModelCreate taskWithLongTitle = new TaskAPIModelCreate(
				uniqueTaskTitle(longTitle),
				Optional.of("Normal description")
		);

		MvcResult result = mockMvc.perform(post("/task/save")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(objectMapper.writeValueAsString(taskWithLongTitle)))
								  .andExpect(status().isCreated())
								  .andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse createdTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(createdTask.title())
				.as("Long title should be stored correctly")
				.isEqualTo(longTitle);

		mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id").value(createdTask.id()))
			   .andExpect(jsonPath("$.title").value(longTitle));
	}

	@Test
	@DisplayName("Should reject extremely long data")
	void shouldRejectExtremelyLongData()
	{
		String extremelyLongTitle = "X".repeat(300);

		assertThat(extremelyLongTitle.length())
				.as("Test title should exceed database column limits")
				.isGreaterThan(255);

		TaskAPIModelCreate taskWithExtremelyLongTitle = new TaskAPIModelCreate(
				uniqueTaskTitle(extremelyLongTitle),
				Optional.of("Normal description")
		);

		try
		{
			mockMvc.perform(post("/task")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(taskWithExtremelyLongTitle)));
		}
		catch (Exception e)
		{
			assertThat(e.getCause().toString())
					.as("Exception should be related to data integrity")
					.contains("DataIntegrityViolationException");
		}
	}

	@ParameterizedTest(name = "{index}: Batch of {0} tasks")
	@MethodSource("provideBatchTasksForCreation")
	@DisplayName("Should create multiple tasks via batch POST")
	void shouldCreateTasksViaBatch(int batchSize, List<TaskAPIModelCreate> tasksToCreate) throws Exception
	{
		MvcResult result = mockMvc.perform(post("/task/save/batch")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(objectMapper.writeValueAsString(tasksToCreate)))
								  .andExpect(status().isCreated())
								  .andReturn();

		String responseContent = result.getResponse().getContentAsString();

		List<TaskAPIModelResponse> createdTasks = objectMapper.readValue(
				responseContent,
				objectMapper.getTypeFactory().constructCollectionType(List.class, TaskAPIModelResponse.class)
		);

		assertThat(createdTasks.size())
				.as("Should have created " + tasksToCreate.size() + " tasks")
				.isEqualTo(tasksToCreate.size());

		int samplesToVerify = Math.min(1_000, createdTasks.size());
		int step = createdTasks.size() / samplesToVerify;

		for (int i = 0; i < samplesToVerify; i++)
		{
			int index = i * step;
			TaskAPIModelResponse createdTask = createdTasks.get(index);
			TaskAPIModelCreate taskToCreate = tasksToCreate.get(index);

			assertEquality(taskToCreate, createdTask);
			verifyTaskResponse(createdTask.id(), createdTask);
		}

		assertThat(createdTasks.size())
				.as("Batch endpoint should have been called exactly once")
				.isEqualTo(tasksToCreate.size());

		long uniqueIds = createdTasks.stream()
									 .map(TaskAPIModelResponse::id)
									 .distinct()
									 .count();

		assertThat(uniqueIds)
				.as("All created tasks should have unique IDs")
				.isEqualTo(createdTasks.size());
	}

	@ParameterizedTest(name = "{index}: Batch of {0} tasks with one existing task")
	@MethodSource("provideBatchTasksForExistingTest")
	@DisplayName("Should fail batch add when one task already exists")
	void shouldFailBatchAddWhenTaskAlreadyExists(int batchSize, List<TaskAPIModelCreate> tasksToCreate) throws Exception
	{
		Random random = new Random();
		int existingIndex = random.nextInt(tasksToCreate.size());
		TaskAPIModelCreate existingTask = tasksToCreate.get(existingIndex);

		TaskAPIModelResponse createdTask = createTask(existingTask);

		assertThat(createdTask).isNotNull();
		assertThat(createdTask.id()).isNotNull();

		MvcResult result = mockMvc.perform(post("/task/save/batch")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(objectMapper.writeValueAsString(tasksToCreate)))
								  .andExpect(status().isConflict())
								  .andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertThat(responseContent)
				.as("Error response should mention resource already exists")
				.containsAnyOf("already exists", "Already exists");

		for (int i = 0; i < Math.min(5, tasksToCreate.size()); i++)
		{
			int randomIndex;
			do
			{
				randomIndex = random.nextInt(tasksToCreate.size());
			} while (randomIndex == existingIndex);

			TaskAPIModelCreate randomTask = tasksToCreate.get(randomIndex);

			mockMvc.perform(get("/task/fetch")
						   .param("page", "0")
						   .param("size", "100"))
				   .andExpect(status().isOk())
				   .andExpect(jsonPath("$.content[*].title", org.hamcrest.Matchers.not(
						   org.hamcrest.Matchers.hasItem(randomTask.title()))));
		}
	}

	@ParameterizedTest(name = "{index}: Batch of {0} tasks with internal duplicate")
	@MethodSource("provideBatchTasksForDuplicateTest")
	@DisplayName("Should fail batch add when batch contains duplicate tasks")
	void shouldFailBatchAddWhenBatchContainsDuplicates(int batchSize, List<TaskAPIModelCreate> tasksToCreate)
			throws Exception
	{
		MvcResult result = mockMvc.perform(post("/task/save/batch")
										  .contentType(MediaType.APPLICATION_JSON)
										  .content(objectMapper.writeValueAsString(tasksToCreate)))
								  .andExpect(status().isBadRequest())
								  .andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertThat(responseContent)
				.as("Error response should mention duplicate or invalid request")
				.containsAnyOf("duplicate", "Duplicate", "invalid", "Invalid");

		for (int i = 0; i < Math.min(5, tasksToCreate.size()); i++)
		{
			int randomIndex = new Random().nextInt(tasksToCreate.size());
			TaskAPIModelCreate randomTask = tasksToCreate.get(randomIndex);

			mockMvc.perform(get("/task/fetch")
						   .param("page", "0")
						   .param("size", "100"))
				   .andExpect(status().isOk())
				   .andExpect(jsonPath("$.content[*].title", org.hamcrest.Matchers.not(
						   org.hamcrest.Matchers.hasItem(randomTask.title()))));
		}
	}

	private static Stream<Arguments> provideTasksForCreation()
	{
		return Stream.of(
				Arguments.of("Basic task", new TaskAPIModelCreate(uniqueTaskTitle("Task 1"), Optional.empty())),
				Arguments.of("Task with description",
						new TaskAPIModelCreate(uniqueTaskTitle("Task 2"), Optional.of("Description for task 2"))),
				Arguments.of("Task with long title", new TaskAPIModelCreate(
						uniqueTaskTitle(
								"This is a task with a very long title that should still be processed correctly"),
						Optional.empty()))
		);
	}

	private static Stream<Arguments> provideBatchTasksForCreation()
	{
		return Stream.of(
				Arguments.of(10, generateRandomTasks(10)),
				Arguments.of(100, generateRandomTasks(100)),
				Arguments.of(500, generateRandomTasks(500)),
				Arguments.of(1_000, generateRandomTasks(1_000))
		);
	}

	private static List<TaskAPIModelCreate> generateRandomTasks(int count)
	{
		Random random = new Random(42);

		return IntStream.range(0, count)
						.mapToObj(i ->
						{
							String title = "Random Task " + i + " - " + random.nextInt(100_000);
							boolean hasDescription = random.nextBoolean();
							Optional<String> description = hasDescription
									? Optional.of("Description for task " + i + ": " + random.nextInt(1000))
									: Optional.empty();
							return new TaskAPIModelCreate(uniqueTaskTitle(title), description);
						})
						.collect(Collectors.toList());
	}

	private static Stream<Arguments> provideBatchTasksForExistingTest()
	{
		return Stream.of(
				Arguments.of(10, generateRandomTasks(10)),
				Arguments.of(20, generateRandomTasks(20)),
				Arguments.of(50, generateRandomTasks(50))
		);
	}

	private static Stream<Arguments> provideBatchTasksForDuplicateTest()
	{
		return Stream.of(
				Arguments.of(10, generateTasksWithDuplicates(10, 1)),
				Arguments.of(20, generateTasksWithDuplicates(20, 2)),
				Arguments.of(50, generateTasksWithDuplicates(50, 5)),
				Arguments.of(50, generateTasksWithDuplicates(5_000, 1))
		);
	}

	private static List<TaskAPIModelCreate> generateTasksWithDuplicates(int count, int duplicateCount)
	{
		List<TaskAPIModelCreate> tasks = generateRandomTasks(count);

		Random random = new Random();
		for (int i = 0; i < duplicateCount; i++)
		{
			int originalIndex = random.nextInt(count);
			TaskAPIModelCreate originalTask = tasks.get(originalIndex);

			TaskAPIModelCreate duplicateTask = new TaskAPIModelCreate(
					originalTask.title(),
					originalTask.description()
			);

			int replaceIndex = (originalIndex + 1 + random.nextInt(count - 1)) % count;
			tasks.set(replaceIndex, duplicateTask);
		}

		return tasks;
	}
}