package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.handler;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.RegisterTagCreation;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.AggregateCreationHandler;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.plan.AggregateCreationPlan;
import org.springframework.stereotype.Component;

@Component
final class RegisterTagCreationHandler
		implements AggregateCreationHandler<TagDomainModelCreate, RegisterTagCreation>
{
	@Override
	public AggregateCreationPlan<TagDomainModelCreate> apply(final RegisterTagCreation payload)
	{
		return AggregateCreationPlan.rootOnly(TagDomainModelCreate.of(payload.name()));
	}
}
