package de.gupta.clean.crud.implementation.examples.task.useCases.crud;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository.TaskJpaRepository;
import de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.domain.RegisterTaskCreation;
import de.gupta.clean.crud.template.useCases.operation.creation.api.application.CreationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.violation.CreationPolicyViolation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Task Creation Tests")
class TaskCreationITCase extends AbstractTaskITCase
{
	@Autowired
	@Qualifier("taskCreationApplicationController")
	private CreationApplicationController<Long, TaskDomainModel> taskCreationApplicationController;

	@Autowired
	private TaskJpaRepository taskJpaRepository;

	@Autowired
	private NoteJpaRepository noteJpaRepository;

	@Test
	@Tag(FAST)
	@DisplayName("Should create a task with owned satellites through an internal creation")
	void shouldCreateTaskWithOwnedSatellitesThroughInternalCreation() throws Exception
	{
		var title = uniqueTaskTitle("task-creation");
		var noteOne = "note-one-" + System.currentTimeMillis();
		var noteTwo = "note-two-" + System.nanoTime();

		var created = taskCreationApplicationController.createInternalCommand(
				new RegisterTaskCreation(
						title,
						Optional.of("created from creation"),
						Optional.of(7L),
						List.of(noteOne, noteTwo))).createdOrThrow();
		var fetched = fetchTask(created.domainId());

		assertThat(fetched.title()).isEqualTo(title);
		assertThat(fetched.description()).contains("created from creation");
		assertThat(fetched.versions()).hasSize(1);
		assertThat(fetched.versions().stream().findFirst().orElseThrow().version()).isEqualTo(7L);
		assertThat(fetched.notes()).extracting(NoteAPIModelResponse::note).containsExactly(noteOne, noteTwo);
		assertThat(taskJpaRepository.findTitlesByTitleIn(List.of(title))).containsExactly(title);
		assertThat(noteJpaRepository.existsByNote(noteOne)).isTrue();
		assertThat(noteJpaRepository.existsByNote(noteTwo)).isTrue();
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should quarantine authoritative external creation when an owned note requires review")
	void shouldQuarantineAuthoritativeExternalCreationWhenOwnedNoteRequiresReview()
	{
		var title = uniqueTaskTitle("task-creation-quarantine");
		var quarantinedNote = "quarantine:broker-payload";

		var result = taskCreationApplicationController.createAuthoritativeExternalEventWithResult(
				new RegisterTaskCreation(
						title,
						Optional.of("should not persist"),
						Optional.empty(),
						List.of(quarantinedNote)));

		assertThat(result.quarantined()).isTrue();
		assertThat(result.created()).isEmpty();
		assertThat(result.quarantineRequest()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().violations())
				.extracting(CreationPolicyViolation::message)
				.containsExactly("Authoritative note creation requires manual review");
		assertThat(taskJpaRepository.findTitlesByTitleIn(List.of(title))).isEmpty();
		assertThat(noteJpaRepository.existsByNote(quarantinedNote)).isFalse();
	}
}
