package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.model.TagPersistenceModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class TagExistingDomainModelsSupplier
		implements Supplier<Collection<TagDomainModel>>
{
	private final TagJpaRepository repository;
	private final DomainPersistenceModelAdapter<TagDomainModel, TagPersistenceModel> modelAdapter;

	@Override
	public Collection<TagDomainModel> get()
	{
		return repository.findAll()
		                 .stream()
		                 .map(modelAdapter::toDomainModel)
		                 .toList();
	}

	TagExistingDomainModelsSupplier(
			final TagJpaRepository repository,
			final DomainPersistenceModelAdapter<TagDomainModel, TagPersistenceModel> modelAdapter)
	{
		this.repository = repository;
		this.modelAdapter = modelAdapter;
	}
}