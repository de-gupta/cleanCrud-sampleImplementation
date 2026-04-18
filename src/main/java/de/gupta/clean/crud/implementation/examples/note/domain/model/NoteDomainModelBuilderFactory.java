package de.gupta.clean.crud.implementation.examples.note.domain.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class NoteDomainModelBuilderFactory implements
		ModelBuilderFactory<NoteDomainModel, NoteDomainModel.NoteDomainModelBuilder>
{
	@Override
	public NoteDomainModel.NoteDomainModelBuilder builder()
	{
		return NoteDomainModelImpl.builder();
	}
}