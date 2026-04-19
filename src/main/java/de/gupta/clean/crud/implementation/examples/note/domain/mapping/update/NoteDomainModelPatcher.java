package de.gupta.clean.crud.implementation.examples.note.domain.mapping.update;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class NoteDomainModelPatcher implements DomainModelPatcher<NoteDomainModel, NoteDomainModelUpdatePatch>
{
	private final ModelBuilderFactory<NoteDomainModel, NoteDomainModel.NoteDomainModelBuilder> modelBuilderFactory;

	@Override
	public NoteDomainModel patchModel(final NoteDomainModel originalModel,
	                                  final NoteDomainModelUpdatePatch updatePatch)
	{
		return modelBuilderFactory.builder()
		                          .withNote(updatePatch.note().orElse(originalModel.note()))

		                          .build();
	}

	NoteDomainModelPatcher(
			final ModelBuilderFactory<NoteDomainModel, NoteDomainModel.NoteDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}