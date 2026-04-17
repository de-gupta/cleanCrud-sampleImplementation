package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.save.facade;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto.TaskVersionAPIModelResponse;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.DomainToAPIResponseAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SaveService;
import de.gupta.clean.crud.template.useCases.crud.save.facade.AbstractSaveServiceFacade;
import de.gupta.clean.crud.template.useCases.crud.save.facade.SaveServiceFacade;
import org.springframework.stereotype.Component;

@Component
final class TaskVersionSaveServiceFacade extends
		AbstractSaveServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse,
				TaskVersionDomainModelCreate, TaskVersionDomainModelResponse, Long>
		implements SaveServiceFacade<TaskVersionAPIModelCreate, TaskVersionAPIModelResponse>
{
	TaskVersionSaveServiceFacade(
			final SaveService<TaskVersionDomainModelCreate, TaskVersionDomainModelResponse, Long> service,
			final APIToDomainCreateAdapter<TaskVersionAPIModelCreate, TaskVersionDomainModelCreate> createMapper,
			final DomainToAPIResponseAdapter<TaskVersionAPIModelResponse, Long, TaskVersionDomainModelResponse> responseMapper)
	{
		super(service, createMapper, responseMapper);
	}
}