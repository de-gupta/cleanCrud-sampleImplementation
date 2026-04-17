package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPortAdapter;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateMutationPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateMutationPortAdapter;
import de.gupta.clean.crud.template.useCases.crud.delete.application.service.DeletePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TaskCrudPortsConfiguration
{
	@Bean
	@Qualifier("taskAggregateMutationPort")
	AggregateMutationPort<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch> taskAggregateMutationPort(
			@Qualifier("taskSavePersistenceService") final SavePersistenceService<Long, TaskDomainModel> savePersistenceService,
			@Qualifier("taskUpdatePersistenceService") final UpdatePersistenceService<Long, TaskDomainModel> updatePersistenceService,
			@Qualifier("taskDeletePersistenceService") final DeletePersistenceService<Long> deletePersistenceService)
	{
		return AggregateMutationPortAdapter.withPersistenceServices(savePersistenceService, updatePersistenceService,
				deletePersistenceService);
	}

	@Bean
	@Qualifier("taskAggregateFetchPort")
	AggregateFetchPort<Long, TaskDomainModel> taskAggregateFetchPort(
			@Qualifier("taskFetchPersistenceService") final FetchPersistenceService<Long, TaskDomainModel> fetchPersistenceService)
	{
		return AggregateFetchPortAdapter.withPersistenceService(fetchPersistenceService);
	}
}