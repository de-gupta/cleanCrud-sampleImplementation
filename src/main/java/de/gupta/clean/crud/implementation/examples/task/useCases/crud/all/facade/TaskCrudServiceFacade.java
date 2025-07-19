package de.gupta.clean.crud.implementation.examples.task.useCases.crud.all.facade;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.all.application.service.CrudService;
import de.gupta.clean.crud.template.useCases.crud.all.facade.AbstractCrudServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.all.facade.CrudServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.all.facade.adapter.model.CrudAPIDomainModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import org.springframework.stereotype.Component;

@Component
final class TaskCrudServiceFacade extends
		AbstractCrudServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long,
				TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long>
		implements CrudServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskCrudServiceFacade(
			final CrudService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long> service,
			final CrudAPIDomainModelAdapter<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse,
					Long, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse> modelAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, modelAdapter, idAdapter);
	}
}