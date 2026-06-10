package de.gupta.clean.crud.implementation.examples.note.useCases.operation.creation.configuration.policy;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.policy.invariant.CreationInvariantPolicy;
import de.gupta.clean.crud.template.useCases.operation.domain.model.OperationSource;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.policy.invariant.InvariantViolation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class NoteCreationPolicyConfiguration
{
	private static final String QUARANTINE_PREFIX = "quarantine:";

	@Bean
	@Qualifier("noteCreationInvariantPolicy")
	CreationInvariantPolicy<NoteDomainModel> noteCreationInvariantPolicy()
	{
		return (source, afterModel) -> source == OperationSource.AUTHORITATIVE_EXTERNAL_EVENT
				&& afterModel.note().startsWith(QUARANTINE_PREFIX)
				? List.of(InvariantViolation.hard("Authoritative note creation requires manual review"))
				: List.of();
	}
}