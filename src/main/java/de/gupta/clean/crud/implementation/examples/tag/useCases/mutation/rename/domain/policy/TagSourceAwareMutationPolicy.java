package de.gupta.clean.crud.implementation.examples.tag.useCases.mutation.rename.domain.policy;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.model.exceptions.security.AccessDeniedException;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import de.gupta.clean.crud.template.useCases.mutation.domain.model.MutationSource;
import de.gupta.clean.crud.template.useCases.mutation.domain.policy.SourceAwareMutationPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("tagSourceAwareMutationPolicy")
final class TagSourceAwareMutationPolicy implements SourceAwareMutationPolicy<TagDomainModel>
{
	private static final String MANAGED_PREFIX = "managed:";

	private final PatchPolicy<TagDomainModel> patchPolicy;

	@Override
	public void validate(
			final MutationSource source,
			final TagDomainModel beforeModel,
			final TagDomainModel afterModel)
	{
		patchPolicy.validatePatchAttempt(beforeModel, afterModel);
		validateManagedNamespaceAccess(source, afterModel);
	}

	private void validateManagedNamespaceAccess(
			final MutationSource source,
			final TagDomainModel afterModel)
	{
		if (!afterModel.name().startsWith(MANAGED_PREFIX))
		{
			return;
		}
		if (source == MutationSource.AUTHORITATIVE_EXTERNAL_EVENT)
		{
			return;
		}
		throw AccessDeniedException.withMessage(
				"Only authoritative external events may assign managed tag names");
	}

	TagSourceAwareMutationPolicy(@Qualifier("tagPatchPolicy") final PatchPolicy<TagDomainModel> patchPolicy)
	{
		this.patchPolicy = patchPolicy;
	}
}
