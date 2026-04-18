package de.gupta.clean.crud.implementation.examples.version.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.AggregateCrudDefinitions;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateMutationPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class VersionCrudDefinitionConfiguration
{
	@Bean
	@Qualifier("versionAggregateCrudDefinition")
	AggregateCrudDefinition<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch, VersionDomainModelResponse> versionAggregateCrudDefinition(
			@Qualifier("versionAggregateMutationPort") final AggregateMutationPort<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch> mutationPort,
			@Qualifier("versionAggregateFetchPort") final AggregateFetchPort<Long, VersionDomainModel> fetchPort,
			@Qualifier("versionDomainModelBuilder") final DomainModelBuilder<VersionDomainModelCreate, VersionDomainModel> createBuilder,
			@Qualifier("versionDomainModelPatcher") final DomainModelPatcher<VersionDomainModel, VersionDomainModelUpdatePatch> patcher,
			@Qualifier("versionDomainResponseBuilder") final DomainResponseBuilder<VersionDomainModel, VersionDomainModelResponse> responseBuilder,
			@Qualifier("versionInsertionPolicy") final InsertionPolicy<VersionDomainModel> insertionPolicy,
			@Qualifier("versionPatchPolicy") final PatchPolicy<VersionDomainModel> patchPolicy,
			@Qualifier("versionDeletionPolicy") final DeletionPolicy<VersionDomainModel> deletionPolicy,
			@Qualifier("versionDomainSecurityPolicy") final DomainSecurityPolicy<VersionDomainModel> securityPolicy,
			@Qualifier("versionDuplicateDefinition") final DuplicateDefinition<VersionDomainModel> duplicateDefinition)
	{
		return AggregateCrudDefinitions
				.<Long, VersionDomainModel, VersionDomainModelCreate, VersionDomainModelUpdatePatch, VersionDomainModelResponse>aggregateCrudDefinition()
				.mutationPort(mutationPort)
				.fetchPort(fetchPort)
				.createBuilder(createBuilder)
				.patcher(patcher)
				.responseBuilder(responseBuilder)
				.insertionPolicy(insertionPolicy)
				.patchPolicy(patchPolicy)
				.deletionPolicy(deletionPolicy)
				.securityPolicy(securityPolicy)
				.duplicateDefinition(duplicateDefinition)
				.build();
	}
}