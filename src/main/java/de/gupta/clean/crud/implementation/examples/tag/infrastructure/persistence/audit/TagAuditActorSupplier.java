package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.audit;

import de.gupta.clean.crud.template.infrastructure.persistence.history.audit.AuditActor;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("tagAuditActorSupplier")
public final class TagAuditActorSupplier implements AuditActorSupplier
{
	@Override
	public AuditActor get()
	{
		// TODO return the current audit actor for this module, for example from a security or request context.
		return null;
	}
}
