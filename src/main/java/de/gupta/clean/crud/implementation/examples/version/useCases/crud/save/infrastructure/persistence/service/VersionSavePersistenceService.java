package de.gupta.clean.crud.implementation.examples.version.useCases.crud.save.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.save.application.service.SavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.AbstractSavePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class VersionSavePersistenceService extends
		AbstractSavePersistenceService<Long, VersionDomainModel, UUID, VersionPersistenceModel>
		implements SavePersistenceService<Long, VersionDomainModel>
{
	VersionSavePersistenceService(
			final SavePersistenceModelRepository<VersionPersistenceModel> repository,
			final DomainPersistenceModelAdapter<VersionDomainModel, VersionPersistenceModel> modelAdapter,
			@Qualifier("versionDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(repository, modelAdapter, idManagement);
	}
}