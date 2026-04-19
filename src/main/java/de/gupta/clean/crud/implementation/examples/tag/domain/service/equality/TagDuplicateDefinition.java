package de.gupta.clean.crud.implementation.examples.tag.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.KeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class TagDuplicateDefinition
		implements KeyBasedDuplicateDefinition<TagDomainModel, TagDuplicateKey>
{
	@Override
	public TagDuplicateKey duplicateKeyOf(final TagDomainModel model)
	{
		// TODO from Template: replace this default duplicate key with the business key your API should use.
		// TODO from Template: if key-based duplicate detection does not fit this domain, delete this class and implement DuplicateDefinition directly.
		return new TagDuplicateKey(
				model.name()
		);
	}
}