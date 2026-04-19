package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.delete.api.application;

import de.gupta.clean.crud.template.useCases.crud.delete.api.application.AbstractDeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.application.DeleteApplicationController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
final class TagDeleteApplicationController extends AbstractDeleteApplicationController<Long>
		implements DeleteApplicationController<Long>
{
	TagDeleteApplicationController(
			@Qualifier("tagDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}