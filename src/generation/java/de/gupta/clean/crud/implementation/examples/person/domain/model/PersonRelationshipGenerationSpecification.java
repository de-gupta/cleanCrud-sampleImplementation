package de.gupta.clean.crud.implementation.examples.person.domain.model;

import de.gupta.clean.crud.generator.code.generation.orchestration.configuration.RelationshipReconciliationStrategy;
import de.gupta.clean.crud.generator.code.generation.orchestration.configuration.specification.AggregateGenerationSpec;
import de.gupta.clean.crud.generator.code.generation.orchestration.configuration.specification.AggregateGenerationSpecs;
import de.gupta.clean.crud.generator.code.generation.orchestration.configuration.specification.CodeGenerationSpecification;
import de.gupta.clean.crud.generator.code.generation.orchestration.configuration.specification.Relationship;
import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionModel;

import java.util.UUID;

public final class PersonRelationshipGenerationSpecification implements CodeGenerationSpecification
{
	@Override
	public AggregateGenerationSpec specification()
	{
		return AggregateGenerationSpecs.aggregate(PersonModel.class)
				.rootApiIdType(Long.class)
				.rootDomainIdType(Long.class)
				.rootPersistenceIdType(UUID.class)
				.relationship(Relationship.referenced("tag", TagModel.class)
						.apiIdType(Long.class)
						.domainIdType(Long.class)
						.persistenceIdType(UUID.class))
				.relationship(Relationship.referenced("currentVersion", VersionModel.class)
						.apiIdType(Long.class)
						.domainIdType(Long.class)
						.persistenceIdType(UUID.class))
				.relationship(Relationship.referenced("lastKnownVersion", VersionModel.class)
						.apiIdType(Long.class)
						.domainIdType(Long.class)
						.persistenceIdType(UUID.class))
				.relationship(Relationship.owned("notes", NoteModel.class)
						.apiIdType(Long.class)
						.domainIdType(Long.class)
						.persistenceIdType(UUID.class)
						.reconciliationStrategy(RelationshipReconciliationStrategy.MERGE_BY_ID))
				.build();
	}
}