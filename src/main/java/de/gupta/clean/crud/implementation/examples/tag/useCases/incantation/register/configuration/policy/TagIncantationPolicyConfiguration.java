package de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.configuration.policy;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.model.IncantationSource;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.access.IncantationAccessPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.consistency.IncantationExternalConsistencyPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.creation.IncantationCreationPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.invariant.IncantationInvariantPolicy;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.profile.IncantationPolicyProfile;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.profile.IncantationPolicyProfileResolver;
import de.gupta.clean.crud.template.useCases.incantation.domain.policy.violation.IncantationViolationHandling;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.invariant.InvariantViolation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
class TagIncantationPolicyConfiguration
{
	private static final String MANAGED_PREFIX = "managed:";
	private static final int SOFT_NAME_LENGTH_LIMIT = 40;
	private static final String DUPLICATE_MESSAGE = "A tag with the same name already exists";

	@Bean
	@Qualifier("tagIncantationPolicyProfileResolver")
	IncantationPolicyProfileResolver tagIncantationPolicyProfileResolver()
	{
		return source -> switch (source)
		{
			case USER_INTENT -> IncantationPolicyProfile.userIntent();
			case INTERNAL_COMMAND, PROCESS_EMITTED_ACTION, ADMINISTRATIVE_REPLAY ->
					IncantationPolicyProfile.internalCommand();
			case AUTHORITATIVE_EXTERNAL_EVENT -> new IncantationPolicyProfile(
					IncantationViolationHandling.ALLOW,
					IncantationViolationHandling.REJECT,
					IncantationViolationHandling.QUARANTINE,
					IncantationViolationHandling.ALLOW,
					IncantationViolationHandling.QUARANTINE);
		};
	}

	@Bean
	@Qualifier("tagIncantationAccessPolicy")
	IncantationAccessPolicy<TagDomainModel> tagIncantationAccessPolicy(
			@Qualifier("tagDomainSecurityPolicy") final DomainSecurityPolicy<TagDomainModel> securityPolicy)
	{
		return (source, afterModel) ->
		{
			if (source == IncantationSource.USER_INTENT && !securityPolicy.isAccessAllowed(afterModel))
			{
				throw new IllegalStateException("Access not allowed");
			}
			if (source == IncantationSource.USER_INTENT && afterModel.name().startsWith(MANAGED_PREFIX))
			{
				throw new IllegalStateException("Only authoritative external events may create managed tag names");
			}
		};
	}

	@Bean
	@Qualifier("tagIncantationCreationPolicy")
	IncantationCreationPolicy<TagDomainModel> tagIncantationCreationPolicy(
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
	@Qualifier("tagIncantationInvariantPolicy")
	IncantationInvariantPolicy<TagDomainModel> tagIncantationInvariantPolicy()
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
	@Qualifier("tagIncantationExternalConsistencyPolicy")
	IncantationExternalConsistencyPolicy<TagDomainModel> tagIncantationExternalConsistencyPolicy()
	{
		return (source, afterModel) ->
		{
			if (source != IncantationSource.AUTHORITATIVE_EXTERNAL_EVENT)
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
