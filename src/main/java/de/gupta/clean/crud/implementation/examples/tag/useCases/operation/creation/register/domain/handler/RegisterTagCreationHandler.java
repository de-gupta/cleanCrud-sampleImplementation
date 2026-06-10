package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.handler;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.RegisterTagCreation;
import de.gupta.clean.crud.template.useCases.operation.creation.domain.handler.CreationHandler;
import org.springframework.stereotype.Component;

@Component
final class RegisterTagCreationHandler
		implements CreationHandler<TagDomainModelCreate, RegisterTagCreation>
{
	@Override
	public TagDomainModelCreate apply(final RegisterTagCreation payload)
	{
		return TagDomainModelCreate.of(payload.name());
	}
}
