package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.update.facade;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdateService;
import de.gupta.clean.crud.template.useCases.crud.update.facade.AbstractUpdateServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.update.facade.UpdateServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionUpdateServiceFacade extends
		AbstractUpdateServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long,
				TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch, TaskVersionDomainModelResponse, Long>
		implements
		UpdateServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelUpdatePatch, TaskVersionAPIModelResponse, Long>
{
	TaskVersionUpdateServiceFacade(
			final UpdateService<TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch, TaskVersionDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TaskVersionAPIModelCreate,
					TaskVersionDomainModelCreate> createAdapter,
			final APIToDomainUpdateAdapter<TaskVersionAPIModelUpdatePatch, TaskVersionDomainModelUpdatePatch> updateAdapter,
			final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long,
					TaskVersionDomainModelResponse> responseAdapter,
			final APIDomainIDAdapter<Long, Long> idAdapter)
	{
		super(service, createAdapter, updateAdapter, responseAdapter, idAdapter);
	}
}