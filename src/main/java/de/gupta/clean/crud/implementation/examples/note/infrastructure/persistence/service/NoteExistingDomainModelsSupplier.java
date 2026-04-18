package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.service;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.model.NotePersistenceModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository.NoteJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.model.DomainPersistenceModelAdapter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Component
final class NoteExistingDomainModelsSupplier
		implements Supplier<Collection<NoteDomainModel>>
{
	private final NoteJpaRepository repository;
	private final DomainPersistenceModelAdapter<NoteDomainModel, NotePersistenceModel> modelAdapter;

	@Override
	public Collection<NoteDomainModel> get()
	{
		return repository.findAll()
		                 .stream()
		                 .map(modelAdapter::toDomainModel)
		                 .toList();
	}

	NoteExistingDomainModelsSupplier(
			final NoteJpaRepository repository,
			final DomainPersistenceModelAdapter<NoteDomainModel, NotePersistenceModel> modelAdapter)
	{
		this.repository = repository;
		this.modelAdapter = modelAdapter;
	}
}