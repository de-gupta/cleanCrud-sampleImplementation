package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskNoteDomainModelUpdatePatchItem;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskVersionDomainModelUpdatePatchItem;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<TaskAPIModelUpdatePatch, TaskDomainModelUpdatePatch>
{
	@Override
	public TaskDomainModelUpdatePatch mapToDomainModelUpdatePatch(final TaskAPIModelUpdatePatch apiModel)
	{
		return new TaskDomainModelUpdatePatch(
				apiModel.title(),
				apiModel.description(),
				apiModel.versions().map(versions -> versions.stream()
				                                            .map(version -> TaskVersionDomainModelUpdatePatchItem.of(
																	version.id(),
																	version.patch()))
				                                            .toList()),
				apiModel.removeVersionIds(),
				apiModel.notes().map(notes -> notes.stream()
				                                   .map(note -> TaskNoteDomainModelUpdatePatchItem.of(
														   note.id(),
														   note.patch()))
				                                   .toList()),
				apiModel.removeNoteIds());
	}
}