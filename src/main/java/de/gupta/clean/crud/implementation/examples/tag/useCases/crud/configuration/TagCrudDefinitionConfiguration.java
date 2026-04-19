package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
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
class TagCrudDefinitionConfiguration
{
	@Bean
	@Qualifier("tagAggregateCrudDefinition")
	AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate,
			TagDomainModelUpdatePatch, TagDomainModelResponse> tagAggregateCrudDefinition(
			@Qualifier("tagAggregateMutationPort") final AggregateMutationPort<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch> mutationPort,
			@Qualifier("tagAggregateFetchPort") final AggregateFetchPort<Long, TagDomainModel> fetchPort,
			@Qualifier("tagDomainModelBuilder") final DomainModelBuilder<TagDomainModelCreate, TagDomainModel> createBuilder,
			@Qualifier("tagDomainModelPatcher") final DomainModelPatcher<TagDomainModel, TagDomainModelUpdatePatch> patcher,
			@Qualifier("tagDomainResponseBuilder") final DomainResponseBuilder<TagDomainModel, TagDomainModelResponse> responseBuilder,
			@Qualifier("tagInsertionPolicy") final InsertionPolicy<TagDomainModel> insertionPolicy,
			@Qualifier("tagPatchPolicy") final PatchPolicy<TagDomainModel> patchPolicy,
			@Qualifier("tagDeletionPolicy") final DeletionPolicy<TagDomainModel> deletionPolicy,
			@Qualifier("tagDomainSecurityPolicy") final DomainSecurityPolicy<TagDomainModel> securityPolicy,
			@Qualifier("tagDuplicateDefinition") final DuplicateDefinition<TagDomainModel> duplicateDefinition)
	{
		return AggregateCrudDefinitions
				.<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch, TagDomainModelResponse>aggregateCrudDefinition()
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