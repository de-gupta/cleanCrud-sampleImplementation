package de.gupta.clean.crud.implementation.examples.task.domain.model;

import de.gupta.clean.crud.implementation.examples.note.useCases.crud.common.dto.NoteAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelResponse;
import de.gupta.clean.crud.template.domain.model.BaseDomainModel;

import java.util.Collection;

public interface TaskDomainModel extends BaseDomainModel, TaskModel
{
	Collection<VersionAPIModelResponse> versions();

	Collection<NoteAPIModelResponse> notes();

	interface TaskDomainModelBuilder extends TaskModelBuilder<TaskDomainModel, TaskDomainModelBuilder>
	{
		TaskDomainModelBuilder withVersions(Collection<VersionAPIModelResponse> versions);

		TaskDomainModelBuilder withNotes(Collection<NoteAPIModelResponse> notes);
	}
}
