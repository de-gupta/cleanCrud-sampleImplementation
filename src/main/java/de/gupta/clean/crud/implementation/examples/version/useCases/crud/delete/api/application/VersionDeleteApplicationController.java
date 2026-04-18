package de.gupta.clean.crud.implementation.examples.version.useCases.crud.delete.api.application;

import de.gupta.clean.crud.template.useCases.crud.delete.api.application.AbstractDeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.application.DeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
final class VersionDeleteApplicationController extends AbstractDeleteApplicationController<Long>
		implements DeleteApplicationController<Long>
{
	VersionDeleteApplicationController(
			@Qualifier("versionDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}