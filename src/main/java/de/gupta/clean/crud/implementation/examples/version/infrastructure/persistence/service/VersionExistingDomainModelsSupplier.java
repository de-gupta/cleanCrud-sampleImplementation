package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.model.VersionPersistenceModel;
import de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.repository.VersionJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class VersionExistingDomainModelsSupplier
		implements Supplier<Collection<VersionDomainModel>>
{
	private final VersionJpaRepository repository;
	private final DomainPersistenceModelAdapter<VersionDomainModel, VersionPersistenceModel> modelAdapter;

	@Override
	public Collection<VersionDomainModel> get()
	{
		return repository.findAll()
		                 .stream()
		                 .map(modelAdapter::toDomainModel)
		                 .toList();
	}

	VersionExistingDomainModelsSupplier(
			final VersionJpaRepository repository,
			final DomainPersistenceModelAdapter<VersionDomainModel, VersionPersistenceModel> modelAdapter)
	{
		this.repository = repository;
		this.modelAdapter = modelAdapter;
	}
}