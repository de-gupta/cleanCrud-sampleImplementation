package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.configuration.policy;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.operation.domain.model.OperationSource;
import de.gupta.clean.crud.template.useCases.operation.domain.policy.invariant.InvariantViolation;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.access.AccessPolicy;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.consistency.ExternalConsistencyPolicy;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.invariant.DomainInvariantPolicy;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.profile.MutationPolicyProfile;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.profile.MutationPolicyProfileResolver;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.transition.MutationTransitionPolicy;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.violation.MutationViolationHandling;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
class TagMutationPolicyConfiguration
{
	private static final String MANAGED_PREFIX = "managed:";
	private static final int SOFT_NAME_LENGTH_LIMIT = 40;

	@Bean
	@Qualifier("tagMutationPolicyProfileResolver")
	MutationPolicyProfileResolver tagMutationPolicyProfileResolver()
	{
		return source -> switch (source)
		{
			case USER_INTENT -> MutationPolicyProfile.userIntent();
			case INTERNAL_COMMAND, PROCESS_EMITTED_ACTION, ADMINISTRATIVE_REPLAY ->
					MutationPolicyProfile.internalCommand();
			case AUTHORITATIVE_EXTERNAL_EVENT -> new MutationPolicyProfile(
					MutationViolationHandling.ALLOW,
					MutationViolationHandling.REJECT,
					MutationViolationHandling.QUARANTINE,
					MutationViolationHandling.ALLOW,
					MutationViolationHandling.QUARANTINE);
		};
	}

	@Bean
	@Qualifier("tagMutationAccessPolicy")
	AccessPolicy<TagDomainModel> tagMutationAccessPolicy(
			@Qualifier("tagDomainSecurityPolicy") final DomainSecurityPolicy<TagDomainModel> securityPolicy)
	{
		return (source, beforeModel, afterModel) ->
		{
			if (source == OperationSource.USER_INTENT)
			{
				if (!securityPolicy.isAccessAllowed(beforeModel) || !securityPolicy.isAccessAllowed(afterModel))
				{
					return Optional.of("Access not allowed");
				}
				if (afterModel.name().startsWith(MANAGED_PREFIX))
				{
					return Optional.of("Only authoritative external events may assign managed tag names");
				}
			}
			return Optional.empty();
		};
	}

	@Bean
	@Qualifier("tagMutationTransitionPolicy")
	MutationTransitionPolicy<TagDomainModel> tagMutationTransitionPolicy(
			@Qualifier("tagPatchPolicy") final PatchPolicy<TagDomainModel> patchPolicy)
	{
		return (_, beforeModel, afterModel) ->
		{
			try
			{
				patchPolicy.validatePatchAttempt(beforeModel, afterModel);
				return Optional.empty();
			}
			catch (RuntimeException e)
			{
				return Optional.ofNullable(e.getMessage()).or(() -> Optional.of("Tag transition rejected"));
			}
		};
	}

	@Bean
	@Qualifier("tagDomainInvariantPolicy")
	DomainInvariantPolicy<TagDomainModel> tagDomainInvariantPolicy()
	{
		return (_, _, afterModel) ->
		{
			var violations = new ArrayList<InvariantViolation>();
			if (afterModel.name().isBlank())
			{
				violations.add(InvariantViolation.hard("Tag name must not be blank"));
			}
			if (afterModel.name().length() > SOFT_NAME_LENGTH_LIMIT)
			{
				violations.add(InvariantViolation.soft("Very long tag names should be reviewed"));
			}
			return List.copyOf(violations);
		};
	}

	@Bean
	@Qualifier("tagExternalConsistencyPolicy")
	ExternalConsistencyPolicy<TagDomainModel> tagExternalConsistencyPolicy()
	{
		return (source, beforeModel, afterModel) ->
		{
			if (source != OperationSource.AUTHORITATIVE_EXTERNAL_EVENT)
			{
				return Optional.empty();
			}
			if (beforeModel.name().startsWith(MANAGED_PREFIX) && !afterModel.name().startsWith(MANAGED_PREFIX))
			{
				return Optional.of("Managed namespace demotion requires operator review");
			}
			return Optional.empty();
		};
	}
}