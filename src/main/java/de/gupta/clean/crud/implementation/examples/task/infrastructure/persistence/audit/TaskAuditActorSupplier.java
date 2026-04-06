package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.audit;

import de.gupta.clean.crud.template.infrastructure.persistence.history.audit.AuditActor;
import de.gupta.clean.crud.template.infrastructure.persistence.history.audit.AuditActors;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskAuditActorSupplier")
final class TaskAuditActorSupplier implements AuditActorSupplier
{
	@Override
	public AuditActor get()
	{
		return AuditActors.withId("Task writer");
	}
}