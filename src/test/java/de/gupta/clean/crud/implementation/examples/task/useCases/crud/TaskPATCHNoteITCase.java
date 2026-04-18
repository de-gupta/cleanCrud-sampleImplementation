package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Task PATCH Note Relationship Endpoint Tests")
class TaskPATCHNoteITCase extends AbstractTaskNoteITCase
{
	@ParameterizedTest(name = "{0}")
	@MethodSource("mutationCases")
	@DisplayName("Should apply MERGE_BY_ID note mutations to the task")
	void shouldApplyMergeByIdNoteMutationsToTheTask(final MutationCase mutationCase) throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Patch Notes"),
				Optional.of("Task patch notes description"),
				List.of(),
				mutationCase.initialNotes()));

		var firstExistingNote = createdTask.notes().stream().findFirst();
		var secondExistingNote = createdTask.notes().stream().skip(1).findFirst();

		var patchedTask = patchTask(
				createdTask.id(),
				mutationCase.patch(firstExistingNote.orElse(null), secondExistingNote.orElse(null)));

		assertThat(patchedTask.notes())
				.as("Patched task should expose the expected number of linked notes")
				.hasSize(mutationCase.expectedNoteValues().size());

		assertThat(patchedTask.notes().stream().map(NoteAPIModelResponse::note).toList())
				.as("Patched task should expose the expected linked note payloads")
				.containsExactlyInAnyOrderElementsOf(mutationCase.expectedNoteValues());
	}

	@Test
	@DisplayName("Should orphan-delete removed notes")
	void shouldOrphanDeleteRemovedNotes() throws Exception
	{
		var createdTask = createTask(TaskAPIModelCreate.of(
				uniqueTaskTitle("Task Patch Notes Removal"),
				Optional.of("Task patch note removal description"),
				List.of(),
				List.of(
						new NoteAPIModelCreate(nextNoteValue()),
						new NoteAPIModelCreate(nextNoteValue()))));
		var noteToRemove = createdTask.notes().iterator().next();

		var patchedTask = patchTask(
				createdTask.id(),
				TaskAPIModelUpdatePatch.of(
						Optional.empty(),
						Optional.empty(),
						Optional.empty(),
						List.of(),
						Optional.empty(),
						List.of(noteToRemove.id())));

		assertThat(patchedTask.notes())
				.as("Patched task should no longer expose the removed note")
				.extracting(NoteAPIModelResponse::id)
				.doesNotContain(noteToRemove.id());

		mockMvc.perform(get("/note/fetch/{id}", noteToRemove.id()))
		       .andExpect(status().isNotFound());
	}

	private static Stream<Arguments> mutationCases()
	{
		var createOnlyValue = nextNoteValue();
		var updateExistingValue = nextNoteValue();
		var keepFirstValue = nextNoteValue();
		var keepSecondValue = nextNoteValue();
		var updateFirstValue = nextNoteValue();
		var createSecondValue = nextNoteValue();
		var removedSecondValue = nextNoteValue();

		return Stream.of(
							 MutationCase.of(
									 "Should create a new note when patch contains an id-less note mutation",
									 List.of(),
									 (_, _) -> TaskAPIModelUpdatePatch.of(
											 Optional.empty(),
											 Optional.empty(),
											 Optional.empty(),
											 List.of(),
											 Optional.of(List.of(notePatchWithoutId(createOnlyValue))),
											 List.of()),
									 List.of(createOnlyValue)),
							 MutationCase.of(
									 "Should update an existing note by id without affecting other linked notes",
									 List.of(
											 new NoteAPIModelCreate(keepFirstValue),
											 new NoteAPIModelCreate(keepSecondValue)),
									 (firstExistingNote, _) -> TaskAPIModelUpdatePatch.of(
											 Optional.empty(),
											 Optional.empty(),
											 Optional.empty(),
											 List.of(),
											 Optional.of(List.of(notePatchWithId(firstExistingNote.id(), updateExistingValue))),
											 List.of()),
									 List.of(updateExistingValue, keepSecondValue)),
							 MutationCase.of(
									 "Should support mixed note create, update, and remove mutations",
									 List.of(
											 new NoteAPIModelCreate(keepFirstValue),
											 new NoteAPIModelCreate(removedSecondValue)),
									 (firstExistingNote, secondExistingNote) -> TaskAPIModelUpdatePatch.of(
											 Optional.empty(),
											 Optional.empty(),
											 Optional.empty(),
											 List.of(),
											 Optional.of(List.of(
													 notePatchWithId(firstExistingNote.id(), updateFirstValue),
													 notePatchWithoutId(createSecondValue))),
											 List.of(secondExistingNote.id())),
									 List.of(updateFirstValue, createSecondValue)))
		             .map(Arguments::of);
	}

	private interface PatchFactory
	{
		TaskAPIModelUpdatePatch create(
				NoteAPIModelResponse firstExistingNote,
				NoteAPIModelResponse secondExistingNote);
	}

	private record MutationCase(
			String description,
			Collection<NoteAPIModelCreate> initialNotes,
			PatchFactory patchFactory,
			Collection<String> expectedNoteValues)
	{
		@Override
		public String toString()
		{
			return description;
		}

		private static MutationCase of(
				final String description,
				final Collection<NoteAPIModelCreate> initialNotes,
				final PatchFactory patchFactory,
				final Collection<String> expectedNoteValues)
		{
			return new MutationCase(description, initialNotes, patchFactory, expectedNoteValues);
		}

		private TaskAPIModelUpdatePatch patch(
				final NoteAPIModelResponse firstExistingNote,
				final NoteAPIModelResponse secondExistingNote)
		{
			return patchFactory.create(firstExistingNote, secondExistingNote);
		}
	}
}