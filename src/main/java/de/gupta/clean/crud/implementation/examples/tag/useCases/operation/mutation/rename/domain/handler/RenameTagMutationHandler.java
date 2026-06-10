package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.domain.handler;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.domain.RenameTagMutation;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.handler.AggregateMutationHandler;
import de.gupta.clean.crud.template.useCases.operation.mutation.domain.plan.AggregateMutationPlan;
import org.springframework.stereotype.Component;

@Component
final class RenameTagMutationHandler implements AggregateMutationHandler<TagDomainModel, RenameTagMutation>
{
	private final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory;

	@Override
	public AggregateMutationPlan<TagDomainModel> apply(
			final TagDomainModel currentModel,
			final RenameTagMutation payload)
	{
		return AggregateMutationPlan.rootOnly(modelBuilderFactory.builder()
		                                                         .withName(payload.name())
		                                                         .build());
	}

	RenameTagMutationHandler(
			final ModelBuilderFactory<TagDomainModel, TagDomainModel.TagDomainModelBuilder> modelBuilderFactory)
	{
		this.modelBuilderFactory = modelBuilderFactory;
	}
}
