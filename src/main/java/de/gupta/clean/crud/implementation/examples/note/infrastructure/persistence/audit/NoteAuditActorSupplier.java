package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.audit;

import de.gupta.clean.crud.template.infrastructure.persistence.history.audit.AuditActor;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("noteAuditActorSupplier")
public final class NoteAuditActorSupplier implements AuditActorSupplier
{
	@Override
	public AuditActor get()
	{
		// TODO return the current audit actor for this module, for example from a security or request context.
		return null;
	}
}
