package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
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
class TaskVersionCrudPortsConfiguration
{
	@Bean
	@Qualifier("taskVersionAggregateMutationPort")
	AggregateMutationPort<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch> taskVersionAggregateMutationPort(
			@Qualifier("taskVersionSavePersistenceService") final SavePersistenceService<Long, TaskVersionDomainModel> savePersistenceService,
			@Qualifier("taskVersionUpdatePersistenceService") final UpdatePersistenceService<Long, TaskVersionDomainModel> updatePersistenceService,
			@Qualifier("taskVersionDeletePersistenceService") final DeletePersistenceService<Long> deletePersistenceService)
	{
		return AggregateMutationPortAdapter.withPersistenceServices(savePersistenceService, updatePersistenceService,
				deletePersistenceService);
	}

	@Bean
	@Qualifier("taskVersionAggregateFetchPort")
	AggregateFetchPort<Long, TaskVersionDomainModel> taskVersionAggregateFetchPort(
			@Qualifier("taskVersionFetchPersistenceService") final FetchPersistenceService<Long, TaskVersionDomainModel> fetchPersistenceService)
	{
		return AggregateFetchPortAdapter.withPersistenceService(fetchPersistenceService);
	}
}
