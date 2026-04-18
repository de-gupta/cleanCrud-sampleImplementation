package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task DELETE Version Relationship Endpoint Tests")
class TaskDELETEVersionITCase extends AbstractTaskVersionITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("deleteCases")
	@DisplayName("Should apply configured version delete semantics")
	void shouldApplyConfiguredVersionDeleteSemantics(final DeleteCase deleteCase) throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Delete Version Semantics"),
				Optional.of("Task delete semantics description"),
				deleteCase.initialVersions()));
		var linkedVersionId = createdTask.versions().stream().findFirst().map(VersionAPIModelResponse::id);

		mockMvc.perform(delete("/task/delete/{id}", createdTask.id()))
		       .andExpect(status().isNoContent());

		mockMvc.perform(get("/task/fetch/{id}", createdTask.id()))
		       .andExpect(status().isNotFound());

		if (linkedVersionId.isPresent())
		{
			mockMvc.perform(get("/version/fetch/{id}", linkedVersionId.orElseThrow()))
			       .andExpect(status().isNotFound());
		}
	}

	private static Stream<Arguments> deleteCases()
	{
		return Stream.of(
							 DeleteCase.of(
									 "Should delete a task without attempting linked version deletion when no version exists",
									 List.of()),
							 DeleteCase.of(
									 "Should cascade-delete the linked version when deleting the task",
									 List.of(new VersionAPIModelCreate(nextVersionValue()))))
		             .map(Arguments::of);
	}

	private record DeleteCase(String description, Collection<VersionAPIModelCreate> initialVersions)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static DeleteCase of(
				final String description,
				final Collection<VersionAPIModelCreate> initialVersions)
		{
			return new DeleteCase(description, initialVersions);
		}
	}
}