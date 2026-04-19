package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.stream.Stream;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag(FAST)
@DisplayName("Task PATCH Endpoint Tests")
class TaskPATCHITCase extends AbstractTaskITCase
{
	@ParameterizedTest(name = "{index}: {0}")
	@MethodSource("provideTasksForUpdate")
	@DisplayName("Should update a task with PATCH")
	void shouldUpdateTaskWithPatch(String testCase, TaskAPIModelUpdatePatch updatePatch) throws Exception
	{
		TaskAPIModelCreate taskToCreate =
				TaskAPIModelCreate.of(uniqueTaskTitle("Original Task"), Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		var result = mockMvc.perform(patch("/task/update/{id}", createdTask.id())
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(updatePatch)))
							.andExpect(status().isOk())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse updatedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		if (updatePatch.title().isPresent())
		{
			assertThat(updatedTask.title())
					.as("Title should be updated")
					.isEqualTo(updatePatch.title().get());
		}
		else
		{
			assertThat(updatedTask.title())
					.as("Title should remain unchanged")
					.isEqualTo(taskToCreate.title());
		}

		if (updatePatch.description().isPresent())
		{
			assertThat(updatedTask.description())
					.as("Description should be updated")
					.isEqualTo(updatePatch.description());
		}
		else
		{
			assertThat(updatedTask.description())
					.as("Description should remain unchanged")
					.isEqualTo(taskToCreate.description());
		}
	}

	@Test
	@DisplayName("Should return 404 when patching non-existent task")
	void shouldReturn404WhenPatchingNonExistentTask() throws Exception
	{
		TaskAPIModelUpdatePatch updatePatch = TaskAPIModelUpdatePatch.of(
				Optional.of("Updated Title"),
				Optional.of("Updated Description")
		);

		mockMvc.perform(patch("/task/update/{id}", 999999L)
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(updatePatch)))
			   .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Should handle empty patch (no fields changed)")
	void shouldHandleEmptyPatch() throws Exception
	{
		TaskAPIModelCreate taskToCreate =
				TaskAPIModelCreate.of(uniqueTaskTitle("Task for Empty Patch"), Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		TaskAPIModelUpdatePatch emptyPatch = TaskAPIModelUpdatePatch.of(Optional.empty(), Optional.empty());

		var result = mockMvc.perform(patch("/task/update/{id}", createdTask.id())
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(emptyPatch)))
							.andExpect(status().isOk())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse updatedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(updatedTask.title())
				.as("Title should remain unchanged with empty patch")
				.isEqualTo(taskToCreate.title());

		assertThat(updatedTask.description())
				.as("Description should remain unchanged with empty patch")
				.isEqualTo(taskToCreate.description());
	}

	@Test
	@DisplayName("Should handle patch with very long data")
	void shouldHandlePatchWithLongData() throws Exception
	{
		TaskAPIModelCreate taskToCreate =
				TaskAPIModelCreate.of(uniqueTaskTitle("Task for Long Patch"), Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(taskToCreate);

		String longTitle = "Long Title for PATCH: This is a longer title that tests the system's ability to handle " +
				"moderately long strings without exceeding database column limits. It should be long enough to " +
				"test edge cases but short enough to fit in the database.";

		String longDescription =
				"Very Long Description for PATCH: This description exceeds 255 characters to test that the system can handle longer descriptions. " +
						"Adding more text to make this description longer. This tests the system's ability to handle very long strings. ".repeat(
								5);

		assertThat(longTitle.length()).isLessThan(255);
		assertThat(longDescription.length()).isGreaterThan(255);
		System.out.println("Description length: " + longDescription.length());

		TaskAPIModelUpdatePatch longDataPatch = TaskAPIModelUpdatePatch.of(
				Optional.of(longTitle),
				Optional.of(longDescription)
		);

		var result = mockMvc.perform(patch("/task/update/{id}", createdTask.id())
									.contentType(MediaType.APPLICATION_JSON)
									.content(objectMapper.writeValueAsString(longDataPatch)))
							.andExpect(status().isOk())
							.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		TaskAPIModelResponse updatedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(updatedTask.title())
				.as("Long title should be updated correctly")
				.isEqualTo(longTitle);

		assertThat(updatedTask.description().orElse(""))
				.as("Long description should be updated correctly")
				.isEqualTo(longDescription);
	}

	private static Stream<Arguments> provideTasksForUpdate()
	{
		return Stream.of(
				Arguments.of("Update title only",
						TaskAPIModelUpdatePatch.of(Optional.of("Updated Title"), Optional.empty())),
				Arguments.of("Update description only",
						TaskAPIModelUpdatePatch.of(Optional.empty(), Optional.of("Updated Description"))),
				Arguments.of("Update both fields",
						TaskAPIModelUpdatePatch.of(Optional.of("New Title"), Optional.of("New Description")))
		);
	}
}