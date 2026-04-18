package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task POST Note Relationship Endpoint Tests")
class TaskPOSTNoteITCase extends AbstractTaskNoteITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("createCases")
	@DisplayName("Should create task with the expected linked notes")
	void shouldCreateTaskWithTheExpectedLinkedNotes(final CreateCase createCase) throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Post Notes"),
				Optional.of("Task post notes description"),
				List.of(),
				createCase.notes()));

		assertThat(createdTask.notes())
				.as("Created task should expose the expected number of linked notes")
				.hasSize(createCase.notes().size());

		assertThat(createdTask.notes().stream().map(note -> note.note()).toList())
				.as("Created task should expose the created note payloads")
				.containsExactlyElementsOf(createCase.notes().stream().map(NoteAPIModelCreate::note).toList());
	}

	private static Stream<Arguments> createCases()
	{
		return Stream.of(
							 CreateCase.of(
									 "Should create a task without notes",
									 List.of()),
							 CreateCase.of(
									 "Should create a task with multiple notes",
									 List.of(
											 new NoteAPIModelCreate(nextNoteValue()),
											 new NoteAPIModelCreate(nextNoteValue()),
											 new NoteAPIModelCreate(nextNoteValue()))))
		             .map(Arguments::of);
	}

	private record CreateCase(
			String description,
			Collection<NoteAPIModelCreate> notes)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static CreateCase of(final String description, final Collection<NoteAPIModelCreate> notes)
		{
			return new CreateCase(description, notes);
		}
	}
}