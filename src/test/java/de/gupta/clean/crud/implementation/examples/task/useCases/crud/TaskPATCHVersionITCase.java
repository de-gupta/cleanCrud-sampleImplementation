package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task PATCH Version Relationship Endpoint Tests")
class TaskPATCHVersionITCase extends AbstractTaskVersionITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("upsertCases")
	@DisplayName("Should create or update the linked version according to current task state")
	void shouldCreateOrUpdateTheLinkedVersionAccordingToCurrentTaskState(final UpsertCase upsertCase)
			throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Patch Version Upsert"),
				Optional.of("Task patch upsert description"),
				upsertCase.initialVersions()));
		var initialLinkedVersionId = createdTask.versions().stream().findFirst().map(VersionAPIModelResponse::id);

		var patchedTask = patchTask(
				createdTask.id(),
				TaskAPIModelUpdatePatch.of(
						Optional.empty(),
						Optional.empty(),
						Optional.of(List.of(new VersionAPIModelUpdatePatch(Optional.empty(),
								Optional.of(upsertCase.updatedVersionValue())))),
						List.of()));

		assertThat(patchedTask.versions())
				.as("Patched task should expose exactly one linked version")
				.hasSize(1);

		var patchedVersion = patchedTask.versions().iterator().next();

		assertThat(patchedVersion.version())
				.as("Patched task should expose the expected updated version value")
				.isEqualTo(upsertCase.updatedVersionValue());

		if (initialLinkedVersionId.isPresent())
		{
			assertThat(patchedVersion.id())
					.as("Updating an existing linked version should keep the same version identity")
					.isEqualTo(initialLinkedVersionId.orElseThrow());
		}
		else
		{
			assertThat(patchedVersion.id())
					.as("Creating a previously absent linked version should assign a new identity")
					.isNotNull();
		}
	}

	@Test
	@DisplayName("Should remove the linked version and orphan-delete it")
	void shouldRemoveTheLinkedVersionAndOrphanDeleteIt() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Patch Version Removal"),
				Optional.of("Task patch removal description"),
				List.of(new VersionAPIModelCreate(nextVersionValue()))));
		var linkedVersion = createdTask.versions().stream().findFirst().orElseThrow();

		var patchedTask = patchTask(
				createdTask.id(),
				TaskAPIModelUpdatePatch.of(
						Optional.empty(),
						Optional.empty(),
						Optional.empty(),
						List.of(linkedVersion.id())));

		assertThat(patchedTask.versions())
				.as("Patched task should expose no linked versions after removal")
				.isEmpty();

		mockMvc.perform(get("/version/fetch/{id}", linkedVersion.id()))
		       .andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Should reject patch requests containing more than one version mutation")
	void shouldRejectPatchRequestsContainingMoreThanOneVersionMutation() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Patch Version Cardinality"),
				Optional.of("Task patch cardinality description")));

		var invalidPatch = TaskAPIModelUpdatePatch.of(
				Optional.empty(),
				Optional.empty(),
				Optional.of(List.of(
						new VersionAPIModelUpdatePatch(Optional.empty(), Optional.of(nextVersionValue())),
						new VersionAPIModelUpdatePatch(Optional.empty(), Optional.of(nextVersionValue())))),
				List.of());

		mockMvc.perform(patch("/task/update/{id}", createdTask.id())
					   .contentType(MediaType.APPLICATION_JSON)
				       .content(objectMapper.writeValueAsString(invalidPatch)))
		       .andExpect(status().isBadRequest());
	}

	private static Stream<Arguments> upsertCases()
	{
		var existingVersion = nextVersionValue();
		var createdVersion = nextVersionValue();
		var updatedVersion = nextVersionValue();

		return Stream.of(
							 UpsertCase.of(
									 "Should create a linked version when patch supplies a version and none exists",
									 List.of(),
									 createdVersion),
							 UpsertCase.of(
									 "Should update the existing linked version when patch supplies a version and one already exists",
									 List.of(new VersionAPIModelCreate(existingVersion)),
									 updatedVersion))
		             .map(Arguments::of);
	}

	private record UpsertCase(
			String description,
			Collection<VersionAPIModelCreate> initialVersions,
			long updatedVersionValue)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static UpsertCase of(
				final String description,
				final Collection<VersionAPIModelCreate> initialVersions,
				final long updatedVersionValue)
		{
			return new UpsertCase(description, initialVersions, updatedVersionValue);
		}
	}
}