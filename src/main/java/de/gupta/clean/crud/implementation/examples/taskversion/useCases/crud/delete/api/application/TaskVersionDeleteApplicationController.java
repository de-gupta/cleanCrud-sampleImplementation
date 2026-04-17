package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.delete.api.application;

import de.gupta.clean.crud.template.useCases.crud.delete.api.application.AbstractDeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.application.DeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionDeleteApplicationController extends AbstractDeleteApplicationController<Long>
		implements DeleteApplicationController<Long>
{
	TaskVersionDeleteApplicationController(
			@Qualifier("taskVersionDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}