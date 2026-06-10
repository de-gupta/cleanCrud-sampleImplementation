package de.gupta.clean.crud.implementation.examples.note.useCases.operation.creation.configuration.policy;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
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
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.invariant.CreationInvariantPolicy;

import java.util.Collection;
import java.util.Objects;

public record NoteCreationPolicyAwareCrudDefinition(
		AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch,
				NoteDomainModelResponse> delegate,
		CreationInvariantPolicy<NoteDomainModel> creationInvariantPolicy)
		implements AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch,
		NoteDomainModelResponse>
{
	public NoteCreationPolicyAwareCrudDefinition
	{
		Objects.requireNonNull(delegate, "delegate");
		Objects.requireNonNull(creationInvariantPolicy, "creationInvariantPolicy");
	}

	@Override
	public AggregateMutationPort<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch> mutationPort()
	{
		return delegate.mutationPort();
	}

	@Override
	public AggregateFetchPort<Long, NoteDomainModel> fetchPort()
	{
		return delegate.fetchPort();
	}

	@Override
	public DomainModelBuilder<NoteDomainModelCreate, NoteDomainModel> createBuilder()
	{
		return delegate.createBuilder();
	}

	@Override
	public DomainModelPatcher<NoteDomainModel, NoteDomainModelUpdatePatch> patcher()
	{
		return delegate.patcher();
	}

	@Override
	public DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> responseBuilder()
	{
		return delegate.responseBuilder();
	}

	@Override
	public InsertionPolicy<NoteDomainModel> insertionPolicy()
	{
		return delegate.insertionPolicy();
	}

	@Override
	public PatchPolicy<NoteDomainModel> patchPolicy()
	{
		return delegate.patchPolicy();
	}

	@Override
	public DeletionPolicy<NoteDomainModel> deletionPolicy()
	{
		return delegate.deletionPolicy();
	}

	@Override
	public DomainSecurityPolicy<NoteDomainModel> securityPolicy()
	{
		return delegate.securityPolicy();
	}

	@Override
	public DuplicateDefinition<NoteDomainModel> duplicateDefinition()
	{
		return delegate.duplicateDefinition();
	}

	@Override
	public PostCommitMutation<Long, NoteDomainModel> postCommitMutation()
	{
		return delegate.postCommitMutation();
	}

	@Override
	public Collection<AggregateRelationshipDefinitionContract<Long, NoteDomainModel, NoteDomainModelCreate,
			NoteDomainModelUpdatePatch>> relationshipDefinitions()
	{
		return delegate.relationshipDefinitions();
	}

	@Override
	public CreationInvariantPolicy<NoteDomainModel> creationInvariantPolicy()
	{
		return creationInvariantPolicy;
	}
}
