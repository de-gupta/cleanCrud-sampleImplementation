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

	@Test
	@DisplayName("Test hashCode consistency with equals for null titles")
	void testHashCodeConsistencyWithNullTitles()
	{
		TaskDomainModel model1 = createTaskModel(null, "Description");
		TaskDomainModel model2 = createTaskModel(null, "Different description");

		assertThat(model1)
				.as("Models with null titles should not be equal")
				.isNotEqualTo(model2);

		// Both models have null titles, so both should have hashCode 0
		assertThat(model1.hashCode())
				.as("Models with null titles should have hashCode 0")
				.isEqualTo(0);

		assertThat(model2.hashCode())
				.as("Models with null titles should have hashCode 0")
				.isEqualTo(0);
	}

	@Test
	@DisplayName("Test hashCode consistency with equals for whitespace in titles")
	void testHashCodeConsistencyWithWhitespace()
	{
		TaskDomainModel model1 = createTaskModel("Task Title", "Description");
		TaskDomainModel model2 = createTaskModel("  Task Title  ", "Different description");
		TaskDomainModel model3 = createTaskModel("", "Description");
		TaskDomainModel model4 = createTaskModel("   ", "Different description");

		// Test whitespace trimming for non-empty titles
		assertThat(model1)
				.as("Models with same title but different whitespace should be equal")
				.isEqualTo(model2);

		assertThat(model1.hashCode())
				.as("Equal objects with different whitespace should have equal hash codes")
				.isEqualTo(model2.hashCode());

		// Test empty string and whitespace-only titles
		assertThat(model3)
				.as("Empty string and whitespace-only titles should be equal after trimming")
				.isEqualTo(model4);

		assertThat(model3.hashCode())
				.as("Empty string and whitespace-only titles should have equal hash codes")
				.isEqualTo(model4.hashCode());
	}

	private static Stream<Arguments> equalsTestCases()
	{
		TaskDomainModel model1 = createTaskModel("Task 1", "Description 1");
		TaskDomainModel model2 = createTaskModel("Task 1", "Different description");
		TaskDomainModel model3 = createTaskModel("task 1", "Description 1"); // Same title but different case
		TaskDomainModel model4 = createTaskModel("Different Task", "Description 1");
		TaskDomainModel emptyTitleModel = createTaskModel("", "Description");
		TaskDomainModel whitespaceTitleModel = createTaskModel("   ", "Description");
		TaskDomainModel leadingWhitespaceTitleModel = createTaskModel("  Task 1", "Description");
		TaskDomainModel trailingWhitespaceTitleModel = createTaskModel("Task 1  ", "Description");
		TaskDomainModel nullTitleModel1 = createTaskModel(null, "Description");
		TaskDomainModel nullTitleModel2 = createTaskModel(null, "Another Description");

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
						model1, "Not a TaskDomainModel", false),

				Arguments.of("Empty string titles should be equal",
						emptyTitleModel, emptyTitleModel, true),

				Arguments.of("Whitespace-only titles should be equal to themselves",
						whitespaceTitleModel, whitespaceTitleModel, true),

				Arguments.of("Empty string and whitespace-only titles should be equal after trimming",
						emptyTitleModel, whitespaceTitleModel, true),

				Arguments.of("Models with null titles should be equal to themselves",
						nullTitleModel1, nullTitleModel1, true),

				Arguments.of("Different models with null titles should not be equal",
						nullTitleModel1, nullTitleModel2, false),

				Arguments.of("Model with null title should not be equal to model with non-null title",
						nullTitleModel1, model1, false),

				Arguments.of(
						"Models with leading whitespace in title should be equal to models with same title without whitespace",
						leadingWhitespaceTitleModel, model1, true),

				Arguments.of(
						"Models with trailing whitespace in title should be equal to models with same title without whitespace",
						trailingWhitespaceTitleModel, model1, true),

				Arguments.of("Models with leading and trailing whitespace in title should be equal",
						leadingWhitespaceTitleModel, trailingWhitespaceTitleModel, true)
		);
	}
}