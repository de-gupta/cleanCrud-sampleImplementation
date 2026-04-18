package de.gupta.clean.crud.implementation.examples.version.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
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
class VersionCrudPortsConfiguration
{
	@Bean
	@Qualifier("versionAggregateMutationPort")
	AggregateMutationPort<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch> versionAggregateMutationPort(
			@Qualifier("versionSavePersistenceService") final SavePersistenceService<Long, VersionDomainModel> savePersistenceService,
			@Qualifier("versionUpdatePersistenceService") final UpdatePersistenceService<Long, VersionDomainModel> updatePersistenceService,
			@Qualifier("versionDeletePersistenceService") final DeletePersistenceService<Long> deletePersistenceService)
	{
		return AggregateMutationPortAdapter.withPersistenceServices(savePersistenceService, updatePersistenceService,
				deletePersistenceService);
	}

	@Bean
	@Qualifier("versionAggregateFetchPort")
	AggregateFetchPort<Long, VersionDomainModel> versionAggregateFetchPort(
			@Qualifier("versionFetchPersistenceService") final FetchPersistenceService<Long, VersionDomainModel> fetchPersistenceService)
	{
		return AggregateFetchPortAdapter.withPersistenceService(fetchPersistenceService);
	}
}