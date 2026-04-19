package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag(FAST)
@DisplayName("Task PUT Endpoint Tests")
class TaskPUTITCase extends AbstractTaskITCase
{
	@Test
	@DisplayName("Should replace a task with PUT")
	void shouldReplaceTaskWithPut() throws Exception
	{
		TaskAPIModelCreate originalTask =
				TaskAPIModelCreate.of(uniqueTaskTitle("Original Task"), Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(originalTask);

		TaskAPIModelCreate replacementTask =
				TaskAPIModelCreate.of(uniqueTaskTitle("Replacement Task"), Optional.of("Replacement Description"));

		mockMvc.perform(put("/task/update/{id}", createdTask.id())
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(replacementTask)))
			   .andExpect(status().isNoContent());

		System.out.println(createdTask.id());

		MvcResult getResult = mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
									 .andExpect(status().isOk())
									 .andReturn();

		String responseContent = getResult.getResponse().getContentAsString();
		TaskAPIModelResponse retrievedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(retrievedTask.title())
				.as("Title should be replaced")
				.isEqualTo(replacementTask.title());

		assertThat(retrievedTask.description())
				.as("Description should be replaced")
				.isEqualTo(replacementTask.description());
	}

	@Test
	@DisplayName("Should handle PUT to non-existent task with 204 status")
	void shouldHandlePutToNonExistentTask() throws Exception
	{
		TaskAPIModelCreate replacementTask =
				TaskAPIModelCreate.of(uniqueTaskTitle("Replacement for Non-existent Task"),
						Optional.of("Replacement Description"));

		mockMvc.perform(put("/task/update/{id}", 999999L)
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(replacementTask)))
			   .andExpect(status().isNoContent());

		mockMvc.perform(get("/task/fetch/{id}", 999999L))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.title").value(replacementTask.title()));
	}

	@Test
	@DisplayName("Should handle PUT with very long data")
	void shouldHandlePutWithLongData() throws Exception
	{
		TaskAPIModelCreate originalTask =
				TaskAPIModelCreate.of(uniqueTaskTitle("Original Task for Long PUT"),
						Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(originalTask);

		String longTitle = "Long Title for PUT: This is a longer title that tests the system's ability to handle " +
				"moderately long strings without exceeding database column limits. It should be long enough to " +
				"test edge cases but short enough to fit in the database.";

		String longDescription =
				"Very Long Description for PUT: This description exceeds 255 characters to test that the system can handle longer descriptions. " +
						"Adding more text to make this description longer. This tests the system's ability to handle very long strings. ".repeat(
								5);

		assertThat(longTitle.length()).isLessThan(255);
		assertThat(longDescription.length()).isGreaterThan(255);
		System.out.println("Description length: " + longDescription.length());

		TaskAPIModelCreate longDataReplacement = TaskAPIModelCreate.of(
				uniqueTaskTitle(longTitle),
				Optional.of(longDescription)
		);

		mockMvc.perform(put("/task/update/{id}", createdTask.id())
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(longDataReplacement)))
			   .andExpect(status().isNoContent());

		MvcResult getResult = mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
									 .andExpect(status().isOk())
									 .andReturn();

		String responseContent = getResult.getResponse().getContentAsString();
		TaskAPIModelResponse retrievedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(retrievedTask.title())
				.as("Long title should be replaced correctly")
				.isEqualTo(longTitle);

		assertThat(retrievedTask.description().orElse(""))
				.as("Long description should be replaced correctly")
				.isEqualTo(longDescription);
	}

	@Test
	@DisplayName("Should handle PUT with empty fields")
	void shouldHandlePutWithEmptyFields() throws Exception
	{
		TaskAPIModelCreate originalTask =
				TaskAPIModelCreate.of(uniqueTaskTitle("Original Task for Empty PUT"),
						Optional.of("Original Description"));
		TaskAPIModelResponse createdTask = createTask(originalTask);

		String replacementJson = String.format(
				"{\"title\":\"%s\",\"description\":\"\"}",
				uniqueTaskTitle("Replacement with Empty Description")
		);

		mockMvc.perform(put("/task/update/{id}", createdTask.id())
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(replacementJson))
			   .andExpect(status().isNoContent());

		MvcResult getResult = mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
									 .andExpect(status().isOk())
									 .andReturn();

		String responseContent = getResult.getResponse().getContentAsString();
		TaskAPIModelResponse retrievedTask = objectMapper.readValue(responseContent, TaskAPIModelResponse.class);

		assertThat(retrievedTask.title())
				.as("Title should be replaced")
				.isEqualTo("Replacement with Empty Description");

		if (retrievedTask.description().isPresent())
		{
			assertThat(retrievedTask.description().get())
					.as("Description should be empty string if present")
					.isEmpty();
		}
		else
		{
			assertThat(retrievedTask.description())
					.as("Description should be empty Optional if not present")
					.isEqualTo(Optional.empty());
		}
	}
}