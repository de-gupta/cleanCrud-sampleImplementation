package de.gupta.clean.crud.implementation.examples.note.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractInsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import org.springframework.stereotype.Component;

@Component
final class NoteInsertionPolicy extends AbstractInsertionPolicy<NoteDomainModel>
		implements InsertionPolicy<NoteDomainModel>
{
	NoteInsertionPolicy(
			final DomainConstraintService<NoteDomainModel> domainConstraintService)
	{
		super(domainConstraintService);
	}
}