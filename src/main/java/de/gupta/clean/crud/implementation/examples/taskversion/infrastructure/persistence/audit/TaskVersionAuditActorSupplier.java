package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.audit;

import de.gupta.clean.crud.template.infrastructure.persistence.history.audit.AuditActor;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskVersionAuditActorSupplier")
public final class TaskVersionAuditActorSupplier implements AuditActorSupplier
{
	@Override
	public AuditActor get()
	{
		// TODO return the current audit actor for this module, for example from a security or request context.
		return null;
	}
}
