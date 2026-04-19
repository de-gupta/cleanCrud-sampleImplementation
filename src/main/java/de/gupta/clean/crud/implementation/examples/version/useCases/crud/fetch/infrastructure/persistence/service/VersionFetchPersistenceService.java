package de.gupta.clean.crud.implementation.examples.version.useCases.crud.fetch.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.AbstractFetchPersistenceService;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class VersionFetchPersistenceService
		extends
		AbstractFetchPersistenceService<Long, VersionDomainModel,
				UUID, VersionPersistenceModel>
		implements
		FetchPersistenceService<Long, VersionDomainModel>
{
	VersionFetchPersistenceService(
			final FetchPersistenceModelRepository<VersionPersistenceModel, UUID> repository,
			final DomainPersistenceModelAdapter<VersionDomainModel,
					VersionPersistenceModel> modelAdapter,
			@Qualifier("versionDomainPersistenceIDAdapter") final
			DomainPersistenceIDAdapter<Long, UUID> idAdapter)
	{
		super(repository, modelAdapter, idAdapter);
	}
}