package de.gupta.clean.crud.implementation.examples.note.domain.service.constraints;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.ConstraintResult;
import de.gupta.clean.crud.template.domain.service.constraints.ExistingModelsConstraintService;
import org.springframework.stereotype.Component;

@Component
final class NoteExistingModelsConstraintService
		implements ExistingModelsConstraintService<NoteDomainModel>
{
	@Override
	public ConstraintResult mayThisResourceBeAdded(final NoteDomainModel model)
	{
		// TODO from Template: add additional insertion-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}

	@Override
	public ConstraintResult mayThisResourceBeChangedTo(
			final NoteDomainModel originalModel,
			final NoteDomainModel newModel)
	{
		// TODO from Template: add additional update-time checks against the existing model set if needed.
		return ConstraintResult.satisfied();
	}
}