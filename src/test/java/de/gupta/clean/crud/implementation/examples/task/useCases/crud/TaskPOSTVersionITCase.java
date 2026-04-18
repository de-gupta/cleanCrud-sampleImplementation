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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task POST Version Relationship Endpoint Tests")
class TaskPOSTVersionITCase extends AbstractTaskVersionITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("saveCases")
	@DisplayName("Should apply configured version create semantics")
	void shouldApplyConfiguredVersionCreateSemantics(final SaveCase saveCase) throws Exception
	{
		var result = saveTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Save Version Semantics"),
				Optional.of("Task save semantics description"),
				saveCase.versions()));

		result.andExpect(status().is(saveCase.expectedStatusCode()));

		if (!saveCase.isSuccessful())
		{
			return;
		}

		var createdTask = readTaskResponse(result.andReturn());

		assertThat(createdTask.versions())
				.as("Created task should expose the expected number of linked versions")
				.hasSize(saveCase.expectedVersionValues().size());

		assertThat(createdTask.versions().stream().map(VersionAPIModelResponse::version))
				.as("Created task should expose the expected linked version values")
				.containsExactlyElementsOf(saveCase.expectedVersionValues());

		for (var createdVersion : createdTask.versions())
		{
			assertThat(fetchVersion(createdVersion.id()))
					.as("A version created through the task aggregate should still be directly fetchable")
					.usingRecursiveComparison()
					.isEqualTo(createdVersion);
		}
	}

	private static Stream<Arguments> saveCases()
	{
		var singleVersion = nextVersionValue();
		var firstInvalidVersion = nextVersionValue();
		var secondInvalidVersion = nextVersionValue();

		return Stream.of(
							 SaveCase.of(
									 "Should save task without any linked versions",
									 List.of(),
									 201,
									 List.of()),
							 SaveCase.of(
									 "Should cascade-create one linked version when one nested version is supplied",
									 List.of(new VersionAPIModelCreate(singleVersion)),
									 201,
									 List.of(singleVersion)),
							 SaveCase.of(
									 "Should reject more than one nested version for a one-to-one relationship",
									 List.of(
											 new VersionAPIModelCreate(firstInvalidVersion),
											 new VersionAPIModelCreate(secondInvalidVersion)),
									 400,
									 List.of()))
		             .map(Arguments::of);
	}

	private record SaveCase(
			String description,
			Collection<VersionAPIModelCreate> versions,
			int expectedStatusCode,
			Collection<Long> expectedVersionValues)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static SaveCase of(
				final String description,
				final Collection<VersionAPIModelCreate> versions,
				final int expectedStatusCode,
				final Collection<Long> expectedVersionValues)
		{
			return new SaveCase(description, versions, expectedStatusCode, expectedVersionValues);
		}

		private boolean isSuccessful()
		{
			return expectedStatusCode == 201;
		}
	}
}