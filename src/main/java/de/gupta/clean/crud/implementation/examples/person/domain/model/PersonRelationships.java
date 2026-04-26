package de.gupta.clean.crud.implementation.examples.person.domain.model;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionModel;
import de.gupta.clean.crud.template.domain.relationship.Relationship;
import de.gupta.clean.crud.template.domain.relationship.Relationships;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.ReconciliationStrategy;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

final class PersonRelationships implements Relationships
{
	@Override
	public Class<?> baseModelClass()
	{
		return PersonModel.class;
	}

	@Override
	public Collection<Relationship> relationships()
	{
		return List.of(
				Relationship.referenced("tag", TagModel.class)
				            .satelliteApiIdType(Long.class)
				            .satelliteDomainIdType(Long.class)
				            .satellitePersistenceIdType(UUID.class)
				            .build(),
				Relationship.referenced("currentVersion", VersionModel.class)
				            .satelliteApiIdType(Long.class)
				            .satelliteDomainIdType(Long.class)
				            .satellitePersistenceIdType(UUID.class)
				            .build(),
				Relationship.referenced("lastKnownVersion", VersionModel.class)
				            .satelliteApiIdType(Long.class)
				            .satelliteDomainIdType(Long.class)
				            .satellitePersistenceIdType(UUID.class)
				            .build(),
				Relationship.owned("notes", NoteModel.class)
				            .satelliteApiIdType(Long.class)
				            .satelliteDomainIdType(Long.class)
				            .satellitePersistenceIdType(UUID.class)
				            .reconciliationStrategy(ReconciliationStrategy.MERGE_BY_ID)
				            .build());
	}
}