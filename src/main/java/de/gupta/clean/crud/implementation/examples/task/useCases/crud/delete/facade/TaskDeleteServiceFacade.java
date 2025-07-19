package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.facade;

import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeleteService;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.AbstractDeleteServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskDeleteServiceFacade")
final class TaskDeleteServiceFacade extends
		AbstractDeleteServiceFacade<Long, Long>
		implements DeleteServiceFacade<Long>
{
	TaskDeleteServiceFacade(
			@Qualifier("taskDeleteService") final DeleteService<Long> service,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, idAdapter);
	}
}