package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
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
class TagCrudPortsConfiguration
{
	@Bean
	@Qualifier("tagAggregateMutationPort")
	AggregateMutationPort<Long, TagDomainModel, TagDomainModelCreate,
			TagDomainModelUpdatePatch> tagAggregateMutationPort(
			@Qualifier("tagSavePersistenceService") final SavePersistenceService<Long, TagDomainModel> savePersistenceService,
			@Qualifier("tagUpdatePersistenceService") final UpdatePersistenceService<Long, TagDomainModel> updatePersistenceService,
			@Qualifier("tagDeletePersistenceService") final DeletePersistenceService<Long> deletePersistenceService)
	{
		return AggregateMutationPortAdapter.withPersistenceServices(
				savePersistenceService,
				updatePersistenceService,
				deletePersistenceService);
	}

	@Bean
	@Qualifier("tagAggregateFetchPort")
	AggregateFetchPort<Long, TagDomainModel> tagAggregateFetchPort(
			@Qualifier("tagFetchPersistenceService") final FetchPersistenceService<Long, TagDomainModel> fetchPersistenceService)
	{
		return AggregateFetchPortAdapter.withPersistenceService(fetchPersistenceService);
	}
}
