package de.gupta.clean.crud.implementation.examples.note.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.domain.service.constraints.DomainConstraintService;
import de.gupta.clean.crud.template.domain.service.crud.policy.AbstractPatchPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.ChangePolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import org.springframework.stereotype.Component;

@Component
final class NotePatchPolicy extends AbstractPatchPolicy<NoteDomainModel>
		implements PatchPolicy<NoteDomainModel>
{
	NotePatchPolicy(
			final ChangePolicy<NoteDomainModel> changePolicy,
			final DomainConstraintService<NoteDomainModel> domainConstraintService)
	{
		super(changePolicy, domainConstraintService);
	}
}