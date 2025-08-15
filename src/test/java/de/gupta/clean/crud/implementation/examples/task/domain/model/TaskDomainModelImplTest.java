package de.gupta.clean.crud.implementation.examples.task.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TaskDomainModelImplTest
{
	@DisplayName("Test equals method with various scenarios")
	@ParameterizedTest(name = "{0}")
	@MethodSource("equalsTestCases")
	void testEquals(String testName, TaskDomainModel model1, Object model2, boolean expectedResult)
	{
		assertThat(model1.equals(model2))
				.as("Equality check between %s and %s", model1.title(),
						model2 instanceof TaskDomainModel ? ((TaskDomainModel) model2).title() : model2)
				.isEqualTo(expectedResult);
	}

	@Test
	@DisplayName("Test hashCode consistency with equals")
	void testHashCodeConsistency()
	{
		TaskDomainModel model1 = createTaskModel("Task Title", "Description");
		TaskDomainModel model2 = createTaskModel("task title", "Different description");

		assertThat(model1)
				.as("Models with case-insensitive equal titles should be equal")
				.isEqualTo(model2);

		assertThat(model1.hashCode())
				.as("Equal objects should have equal hash codes")
				.isEqualTo(model2.hashCode());
	}

	private static TaskDomainModel createTaskModel(String title, String description)
	{
		return TaskDomainModelImpl.builder()
								  .withTitle(title)
								  .withDescription(Optional.ofNullable(description))
								  .build();
	}

	private static Stream<Arguments> equalsTestCases()
	{
		TaskDomainModel model1 = createTaskModel("Task 1", "Description 1");
		TaskDomainModel model2 = createTaskModel("Task 1", "Different description");
		TaskDomainModel model3 = createTaskModel("task 1", "Description 1"); // Same title but different case
		TaskDomainModel model4 = createTaskModel("Different Task", "Description 1");

		return Stream.of(
				Arguments.of("Same object reference should be equal",
						model1, model1, true),

				Arguments.of("Different objects with same title (case-sensitive) should be equal",
						model1, model2, true),

				Arguments.of("Different objects with same title (case-insensitive) should be equal",
						model1, model3, true),

				Arguments.of("Different objects with different titles should not be equal",
						model1, model4, false),

				Arguments.of("Comparison with null should return false",
						model1, null, false),

				Arguments.of("Comparison with different type should return false",
						model1, "Not a TaskDomainModel", false)
		);
	}
}