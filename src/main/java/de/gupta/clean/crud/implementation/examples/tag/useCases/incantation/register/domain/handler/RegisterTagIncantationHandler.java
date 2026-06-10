package de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.domain.handler;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.dto.TagDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.domain.RegisterTagIncantation;
import de.gupta.clean.crud.template.useCases.incantation.domain.handler.IncantationHandler;
import org.springframework.stereotype.Component;

@Component
final class RegisterTagIncantationHandler
		implements IncantationHandler<TagDomainModelCreate, RegisterTagIncantation>
{
	@Override
	public TagDomainModelCreate apply(final RegisterTagIncantation payload)
	{
		return TagDomainModelCreate.of(payload.name());
	}
}
