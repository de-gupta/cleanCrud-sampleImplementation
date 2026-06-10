package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.configuration.policy;

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
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.PostCommitMutation;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateMutationPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.relationship.AggregateRelationshipDefinitionContract;
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

import java.util.Collection;
import java.util.Objects;

public record TagApplicationPolicyAwareCrudDefinition(
		AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch,
				TagDomainModelResponse> delegate,
		MutationPolicyProfileResolver mutationPolicyProfileResolver,
		AccessPolicy<TagDomainModel> mutationAccessPolicy,
		MutationTransitionPolicy<TagDomainModel> mutationTransitionPolicy,
		DomainInvariantPolicy<TagDomainModel> domainInvariantPolicy,
		ExternalConsistencyPolicy<TagDomainModel> externalConsistencyPolicy,
		IncantationPolicyProfileResolver incantationPolicyProfileResolver,
		IncantationAccessPolicy<TagDomainModel> incantationAccessPolicy,
		IncantationCreationPolicy<TagDomainModel> incantationCreationPolicy,
		IncantationInvariantPolicy<TagDomainModel> incantationInvariantPolicy,
		IncantationExternalConsistencyPolicy<TagDomainModel> incantationExternalConsistencyPolicy)
		implements AggregateCrudDefinition<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch,
		TagDomainModelResponse>
{
	public TagApplicationPolicyAwareCrudDefinition
	{
		Objects.requireNonNull(delegate, "delegate");
		Objects.requireNonNull(mutationPolicyProfileResolver, "mutationPolicyProfileResolver");
		Objects.requireNonNull(mutationAccessPolicy, "mutationAccessPolicy");
		Objects.requireNonNull(mutationTransitionPolicy, "mutationTransitionPolicy");
		Objects.requireNonNull(domainInvariantPolicy, "domainInvariantPolicy");
		Objects.requireNonNull(externalConsistencyPolicy, "externalConsistencyPolicy");
		Objects.requireNonNull(incantationPolicyProfileResolver, "incantationPolicyProfileResolver");
		Objects.requireNonNull(incantationAccessPolicy, "incantationAccessPolicy");
		Objects.requireNonNull(incantationCreationPolicy, "incantationCreationPolicy");
		Objects.requireNonNull(incantationInvariantPolicy, "incantationInvariantPolicy");
		Objects.requireNonNull(incantationExternalConsistencyPolicy, "incantationExternalConsistencyPolicy");
	}

	@Override
	public AggregateMutationPort<Long, TagDomainModel, TagDomainModelCreate, TagDomainModelUpdatePatch> mutationPort()
	{
		return delegate.mutationPort();
	}

	@Override
	public AggregateFetchPort<Long, TagDomainModel> fetchPort()
	{
		return delegate.fetchPort();
	}

	@Override
	public DomainModelBuilder<TagDomainModelCreate, TagDomainModel> createBuilder()
	{
		return delegate.createBuilder();
	}

	@Override
	public DomainModelPatcher<TagDomainModel, TagDomainModelUpdatePatch> patcher()
	{
		return delegate.patcher();
	}

	@Override
	public DomainResponseBuilder<TagDomainModel, TagDomainModelResponse> responseBuilder()
	{
		return delegate.responseBuilder();
	}

	@Override
	public InsertionPolicy<TagDomainModel> insertionPolicy()
	{
		return delegate.insertionPolicy();
	}

	@Override
	public PatchPolicy<TagDomainModel> patchPolicy()
	{
		return delegate.patchPolicy();
	}

	@Override
	public DeletionPolicy<TagDomainModel> deletionPolicy()
	{
		return delegate.deletionPolicy();
	}

	@Override
	public DomainSecurityPolicy<TagDomainModel> securityPolicy()
	{
		return delegate.securityPolicy();
	}

	@Override
	public DuplicateDefinition<TagDomainModel> duplicateDefinition()
	{
		return delegate.duplicateDefinition();
	}

	@Override
	public PostCommitMutation<Long, TagDomainModel> postCommitMutation()
	{
		return delegate.postCommitMutation();
	}

	@Override
	public Collection<AggregateRelationshipDefinitionContract<Long, TagDomainModel, TagDomainModelCreate,
			TagDomainModelUpdatePatch>> relationshipDefinitions()
	{
		return delegate.relationshipDefinitions();
	}
}
