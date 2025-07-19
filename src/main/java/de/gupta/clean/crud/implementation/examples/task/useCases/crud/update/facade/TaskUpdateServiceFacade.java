package de.gupta.clean.crud.implementation.examples.task.useCases.crud.update.facade;

import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.facade.AbstractUpdateServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskUpdateServiceFacade extends
		AbstractUpdateServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long,
				TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long>
		implements UpdateServiceFacade<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
{
	TaskUpdateServiceFacade(
			final UpdateService<TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TaskAPIModelCreate, TaskDomainModelCreate> createAdapter,
			final APIToDomainUpdateAdapter<TaskAPIModelUpdatePatch, TaskDomainModelUpdatePatch> updateAdapter,
			final DomainToAPIResponseAdapter<TaskAPIModelResponse, Long, TaskDomainModelResponse> responseAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, createAdapter, updateAdapter, responseAdapter, idAdapter);
	}
}