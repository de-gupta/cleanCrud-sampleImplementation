package de.gupta.clean.crud.implementation.examples.note.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
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
class NoteCrudPortsConfiguration
{
	@Bean
	@Qualifier("noteAggregateMutationPort")
	AggregateMutationPort<Long, NoteDomainModel, NoteDomainModelCreate,
			NoteDomainModelUpdatePatch> noteAggregateMutationPort(
			@Qualifier("noteSavePersistenceService") final SavePersistenceService<Long, NoteDomainModel> savePersistenceService,
			@Qualifier("noteUpdatePersistenceService") final UpdatePersistenceService<Long, NoteDomainModel> updatePersistenceService,
			@Qualifier("noteDeletePersistenceService") final DeletePersistenceService<Long> deletePersistenceService)
	{
		return AggregateMutationPortAdapter.withPersistenceServices(
				savePersistenceService,
				updatePersistenceService,
				deletePersistenceService);
	}

	@Bean
	@Qualifier("noteAggregateFetchPort")
	AggregateFetchPort<Long, NoteDomainModel> noteAggregateFetchPort(
			@Qualifier("noteFetchPersistenceService") final FetchPersistenceService<Long, NoteDomainModel> fetchPersistenceService)
	{
		return AggregateFetchPortAdapter.withPersistenceService(fetchPersistenceService);
	}
}
