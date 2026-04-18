package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task GET Version Relationship Endpoint Tests")
class TaskGETVersionITCase extends AbstractTaskVersionITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("fetchCases")
	@DisplayName("Should hydrate linked versions according to the relationship configuration")
	void shouldHydrateLinkedVersionsAccordingToTheRelationshipConfiguration(final FetchCase fetchCase)
			throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Fetch Version Semantics"),
				Optional.of("Task fetch semantics description"),
				fetchCase.initialVersions()));

		var fetchedTask = fetchTask(createdTask.id());

		assertThat(fetchedTask.versions().stream().map(VersionAPIModelResponse::version))
				.as("Fetched task should expose the expected hydrated version values")
				.containsExactlyElementsOf(fetchCase.expectedVersionValues());
	}

	@Test
	@DisplayName("Should reflect direct version updates when the task is fetched")
	void shouldReflectDirectVersionUpdatesWhenTheTaskIsFetched() throws Exception
	{
		var initialVersion = nextVersionValue();
		var updatedVersion = nextVersionValue();
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Fetch Hydration Version"),
				Optional.of("Task fetch hydration description"),
				List.of(new VersionAPIModelCreate(initialVersion))));
		var linkedVersion = createdTask.versions().stream().findFirst().orElseThrow();

		var versionPatch = VersionAPIModelUpdatePatch.of(Optional.of(updatedVersion));

		mockMvc.perform(patch("/version/update/{id}", linkedVersion.id())
					   .contentType(MediaType.APPLICATION_JSON)
				       .content(objectMapper.writeValueAsString(versionPatch)))
		       .andExpect(status().isOk());

		var fetchedTask = fetchTask(createdTask.id());

		assertThat(fetchedTask.versions())
				.as("Fetched task should still expose exactly one linked version")
				.hasSize(1);

		assertThat(fetchedTask.versions().iterator().next())
				.as("Fetched task should hydrate the latest directly updated version state")
				.usingRecursiveComparison()
				.isEqualTo(new VersionAPIModelResponse(linkedVersion.id(), updatedVersion));
	}

	private static Stream<Arguments> fetchCases()
	{
		var singleVersion = nextVersionValue();

		return Stream.of(
							 FetchCase.of(
									 "Should return an empty version collection when task has no linked version",
									 List.of(),
									 List.of()),
							 FetchCase.of(
									 "Should return one hydrated version when task has a linked version",
									 List.of(new VersionAPIModelCreate(singleVersion)),
									 List.of(singleVersion)))
		             .map(Arguments::of);
	}

	private record FetchCase(
			String description,
			Collection<VersionAPIModelCreate> initialVersions,
			Collection<Long> expectedVersionValues)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static FetchCase of(
				final String description,
				final Collection<VersionAPIModelCreate> initialVersions,
				final Collection<Long> expectedVersionValues)
		{
			return new FetchCase(description, initialVersions, expectedVersionValues);
		}
	}
}
