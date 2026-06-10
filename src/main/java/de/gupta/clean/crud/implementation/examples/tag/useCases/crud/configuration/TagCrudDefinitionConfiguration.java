package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.configuration.policy.TagApplicationPolicyAwareCrudDefinition;
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
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.access.IncantationAccessPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.consistency.IncantationExternalConsistencyPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.creation.IncantationCreationPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.invariant.IncantationInvariantPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.profile.IncantationPolicyProfileResolver;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.access.AccessPolicy;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.consistency.ExternalConsistencyPolicy;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.invariant.DomainInvariantPolicy;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.profile.MutationPolicyProfileResolver;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.transition.MutationTransitionPolicy;
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
			@Qualifier("tagDuplicateDefinition") final DuplicateDefinition<TagDomainModel> duplicateDefinition,
			@Qualifier("tagMutationPolicyProfileResolver") final MutationPolicyProfileResolver mutationPolicyProfileResolver,
			@Qualifier("tagMutationAccessPolicy") final AccessPolicy<TagDomainModel> mutationAccessPolicy,
			@Qualifier("tagMutationTransitionPolicy") final MutationTransitionPolicy<TagDomainModel> mutationTransitionPolicy,
			@Qualifier("tagDomainInvariantPolicy") final DomainInvariantPolicy<TagDomainModel> domainInvariantPolicy,
			@Qualifier("tagExternalConsistencyPolicy") final ExternalConsistencyPolicy<TagDomainModel> externalConsistencyPolicy,
			@Qualifier("tagIncantationPolicyProfileResolver") final IncantationPolicyProfileResolver incantationPolicyProfileResolver,
			@Qualifier("tagIncantationAccessPolicy") final IncantationAccessPolicy<TagDomainModel> incantationAccessPolicy,
			@Qualifier("tagIncantationCreationPolicy") final IncantationCreationPolicy<TagDomainModel> incantationCreationPolicy,
			@Qualifier("tagIncantationInvariantPolicy") final IncantationInvariantPolicy<TagDomainModel> incantationInvariantPolicy,
			@Qualifier("tagIncantationExternalConsistencyPolicy") final IncantationExternalConsistencyPolicy<TagDomainModel> incantationExternalConsistencyPolicy)
	{
		var baseDefinition = AggregateCrudDefinitions
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
				.postCommitMutation(context -> System.out.printf(
						"Tag post-commit mutation: kind=%s, id=%s%n",
						context.kind(),
						context.domainId()))
				.build();

		return new TagApplicationPolicyAwareCrudDefinition(
				baseDefinition,
				mutationPolicyProfileResolver,
				mutationAccessPolicy,
				mutationTransitionPolicy,
				domainInvariantPolicy,
				externalConsistencyPolicy,
				incantationPolicyProfileResolver,
				incantationAccessPolicy,
				incantationCreationPolicy,
				incantationInvariantPolicy,
				incantationExternalConsistencyPolicy);
	}
}
