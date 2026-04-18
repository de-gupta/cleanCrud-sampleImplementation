package de.gupta.clean.crud.implementation.examples.version.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.KeyBasedDuplicateDefinition;
import org.springframework.stereotype.Component;

@Component
final class VersionDuplicateDefinition
		implements KeyBasedDuplicateDefinition<VersionDomainModel, VersionDuplicateKey>
{
	@Override
	public VersionDuplicateKey duplicateKeyOf(final VersionDomainModel model)
	{
		// TODO from Template: replace this default duplicate key with the business key your API should use.
		// TODO from Template: if key-based duplicate detection does not fit this domain, delete this class and implement DuplicateDefinition directly.
		return new VersionDuplicateKey(
				model.version()
		);
	}
}