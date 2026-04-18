package de.gupta.clean.crud.implementation.examples.version.useCases.crud.update.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDAdapter;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import de.gupta.clean.crud.template.useCases.crud.fetch.infrastructure.persistence.service.FetchPersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.save.infrastructure.persistence.service.SavePersistenceModelRepository;
import de.gupta.clean.crud.template.useCases.crud.update.application.service.UpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.AbstractUpdatePersistenceService;
import de.gupta.clean.crud.template.useCases.crud.update.infrastructure.persistence.service.UpdatePersistenceModelRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
final class VersionUpdatePersistenceService
		extends AbstractUpdatePersistenceService<Long, VersionDomainModel, UUID, VersionPersistenceModel>
		implements UpdatePersistenceService<Long, VersionDomainModel>
{
	VersionUpdatePersistenceService(
			final FetchPersistenceModelRepository<VersionPersistenceModel, UUID> fetchRepository,
			final SavePersistenceModelRepository<VersionPersistenceModel> saveRepository,
			final UpdatePersistenceModelRepository<VersionPersistenceModel> updateRepository,
			final DomainPersistenceModelAdapter<VersionDomainModel, VersionPersistenceModel> modelAdapter,
			@Qualifier("versionDomainPersistenceIDAdapter") final DomainPersistenceIDAdapter<Long, UUID> idAdapter,
			@Qualifier("versionDomainPersistenceIDManagement") final DomainPersistenceIDManagement<Long, UUID> idManagement)
	{
		super(fetchRepository, saveRepository, updateRepository, modelAdapter, idAdapter, idManagement);
	}
}