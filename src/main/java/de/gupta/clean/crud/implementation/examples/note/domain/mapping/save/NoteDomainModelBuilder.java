package de.gupta.clean.crud.implementation.examples.note.domain.mapping.save;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class NoteDomainModelBuilder implements DomainModelBuilder<NoteDomainModelCreate, NoteDomainModel>
{
	private final ModelBuilderFactory<NoteDomainModel, NoteDomainModel.NoteDomainModelBuilder> modelBuilderFactory;

	@Override
	public NoteDomainModel toModel(final NoteDomainModelCreate domainModelCreate)
	{
		return modelBuilderFactory.builder()
		                          .withNote(domainModelCreate.note())
		                          .build();
	}

	NoteDomainModelBuilder(
			final ModelBuilderFactory<NoteDomainModel, NoteDomainModel.NoteDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}