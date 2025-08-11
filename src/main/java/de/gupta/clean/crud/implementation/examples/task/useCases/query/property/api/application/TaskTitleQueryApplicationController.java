package de.gupta.clean.crud.implementation.examples.task.useCases.query.property.api.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.query.property.api.application.AbstractPropertyQueryApplicationController;
import de.gupta.clean.crud.template.useCases.query.property.api.application.PropertyQueryApplicationController;
import de.gupta.clean.crud.template.useCases.query.property.facade.PropertyQueryServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskTitleQueryApplicationController
		extends AbstractPropertyQueryApplicationController<String, TaskAPIModelResponse>
		implements PropertyQueryApplicationController<String, TaskAPIModelResponse>
{
	TaskTitleQueryApplicationController(final PropertyQueryServiceFacade<String, TaskAPIModelResponse> service)
	{
		super(service);
	}
}