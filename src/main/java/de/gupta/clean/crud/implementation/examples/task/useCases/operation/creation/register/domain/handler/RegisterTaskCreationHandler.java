package de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.domain.handler;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.operation.creation.register.domain.RegisterTaskCreation;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.CreationHandler;
import org.springframework.stereotype.Component;

@Component
final class RegisterTaskCreationHandler
		implements CreationHandler<TaskDomainModelCreate, RegisterTaskCreation>
{
	@Override
	public TaskDomainModelCreate apply(final RegisterTaskCreation payload)
	{
		return TaskDomainModelCreate.of(
				payload.title(),
				payload.description(),
				payload.version().stream().map(VersionAPIModelCreate::new).toList(),
				payload.notes().stream().map(NoteAPIModelCreate::new).toList());
	}
}
