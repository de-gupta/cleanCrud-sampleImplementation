package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.configuration.policy;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.access.CreationAccessPolicy;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.consistency.CreationExternalConsistencyPolicy;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.creation.CreationPolicy;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.invariant.CreationInvariantPolicy;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.profile.CreationPolicyProfile;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.profile.CreationPolicyProfileResolver;
import de.gupta.clean.crud.template.useCases.operation.domain.model.OperationSource;
import de.gupta.clean.crud.template.useCases.operation.domain.policy.invariant.InvariantViolation;
import de.gupta.clean.crud.template.useCases.operation.domain.policy.violation.ViolationHandling;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
class TagCreationPolicyConfiguration
{
	private static final String MANAGED_PREFIX = "managed:";
	private static final int SOFT_NAME_LENGTH_LIMIT = 40;
	private static final String DUPLICATE_MESSAGE = "A tag with the same name already exists";

	@Bean
	@Qualifier("tagCreationPolicyProfileResolver")
	CreationPolicyProfileResolver tagCreationPolicyProfileResolver()
	{
		return source -> switch (source)
		{
			case USER_INTENT -> CreationPolicyProfile.userIntent();
			case INTERNAL_COMMAND, PROCESS_EMITTED_ACTION, ADMINISTRATIVE_REPLAY ->
					CreationPolicyProfile.internalCommand();
			case AUTHORITATIVE_EXTERNAL_EVENT -> new CreationPolicyProfile(
					ViolationHandling.ALLOW,
					ViolationHandling.REJECT,
					ViolationHandling.QUARANTINE,
					ViolationHandling.ALLOW,
					ViolationHandling.QUARANTINE);
		};
	}

	@Bean
	@Qualifier("tagCreationAccessPolicy")
	CreationAccessPolicy<TagDomainModel> tagCreationAccessPolicy(
			@Qualifier("tagDomainSecurityPolicy") final DomainSecurityPolicy<TagDomainModel> securityPolicy)
	{
		return (source, afterModel) ->
		{
			if (source == OperationSource.USER_INTENT && !securityPolicy.isAccessAllowed(afterModel))
			{
				throw new IllegalStateException("Access not allowed");
			}
			if (source == OperationSource.USER_INTENT && afterModel.name().startsWith(MANAGED_PREFIX))
			{
				throw new IllegalStateException("Only authoritative external events may create managed tag names");
			}
		};
	}

	@Bean
	@Qualifier("tagCreationPolicy")
	CreationPolicy<TagDomainModel> tagCreationPolicy(
			@Qualifier("tagInsertionPolicy") final InsertionPolicy<TagDomainModel> insertionPolicy,
			final TagJpaRepository repository)
	{
		return (_, afterModel) ->
		{
			try
			{
				insertionPolicy.validateInsertion(afterModel);
			}
			catch (RuntimeException e)
			{
				return Optional.ofNullable(e.getMessage()).or(() -> Optional.of("Tag creation rejected"));
			}
			if (repository.existsByName(afterModel.name()))
			{
				return Optional.of(DUPLICATE_MESSAGE);
			}
			return Optional.empty();
		};
	}

	@Bean
	@Qualifier("tagCreationInvariantPolicy")
	CreationInvariantPolicy<TagDomainModel> tagCreationInvariantPolicy()
	{
		return (_, afterModel) ->
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
	@Qualifier("tagCreationExternalConsistencyPolicy")
	CreationExternalConsistencyPolicy<TagDomainModel> tagCreationExternalConsistencyPolicy()
	{
		return (source, afterModel) ->
		{
			if (source != OperationSource.AUTHORITATIVE_EXTERNAL_EVENT)
			{
				return Optional.empty();
			}
			if (!afterModel.name().startsWith(MANAGED_PREFIX))
			{
				return Optional.of("Authoritative tag registration must target the managed namespace");
			}
			return Optional.empty();
		};
	}
}