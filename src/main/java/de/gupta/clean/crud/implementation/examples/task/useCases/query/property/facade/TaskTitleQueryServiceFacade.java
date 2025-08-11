package de.gupta.clean.crud.implementation.examples.task.useCases.query.property.facade;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.query.property.application.service.PropertyQueryService;
import de.gupta.clean.crud.template.useCases.query.property.facade.AbstractPropertyQueryServiceFacade;
import de.gupta.clean.crud.template.useCases.query.property.facade.PropertyQueryServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskTitleQueryServiceFacade
		extends AbstractPropertyQueryServiceFacade<String, Long, TaskDomainModelResponse, TaskAPIModelResponse>
		implements PropertyQueryServiceFacade<String, TaskAPIModelResponse>
{
	TaskTitleQueryServiceFacade(final PropertyQueryService<String, Long, TaskDomainModelResponse> service,
								final DomainToAPIResponseAdapter<TaskAPIModelResponse, Long, TaskDomainModelResponse> responseMapper)
	{
		super(service, responseMapper);
	}
}