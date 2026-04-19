package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(FAST)
@DisplayName("Task GET Note Relationship Endpoint Tests")
class TaskGETNoteITCase extends AbstractTaskNoteITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("fetchCases")
	@DisplayName("Should hydrate notes on task fetch")
	void shouldHydrateNotesOnTaskFetch(final FetchCase fetchCase) throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Get Notes"),
				Optional.of("Task get notes description"),
				List.of(),
				fetchCase.notes()));

		var fetchedTask = fetchTask(createdTask.id());

		assertThat(fetchedTask.notes())
				.as("Fetched task should expose the expected linked notes")
				.extracting(NoteAPIModelResponse::note)
				.containsExactlyElementsOf(fetchCase.notes().stream().map(NoteAPIModelCreate::note).toList());
	}

	private static Stream<Arguments> fetchCases()
	{
		return Stream.of(
							 FetchCase.of(
									 "Should fetch a task without notes",
									 List.of()),
							 FetchCase.of(
									 "Should fetch a task with hydrated notes",
									 List.of(
											 new NoteAPIModelCreate(nextNoteValue()),
											 new NoteAPIModelCreate(nextNoteValue()))))
		             .map(Arguments::of);
	}

	private record FetchCase(
			String description,
			Collection<NoteAPIModelCreate> notes)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static FetchCase of(final String description, final Collection<NoteAPIModelCreate> notes)
		{
			return new FetchCase(description, notes);
		}
	}
}