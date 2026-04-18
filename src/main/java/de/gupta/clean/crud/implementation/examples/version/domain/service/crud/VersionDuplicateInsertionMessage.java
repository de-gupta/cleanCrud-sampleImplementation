package de.gupta.clean.crud.implementation.examples.version.domain.service.crud;

import de.gupta.clean.crud.implementation.examples.version.domain.model.VersionDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateInsertionMessage;
import org.springframework.stereotype.Component;

@Component
final class VersionDuplicateInsertionMessage implements DuplicateInsertionMessage<VersionDomainModel>
{
	@Override
	public String messageIfModelAlreadyExists(final VersionDomainModel versionDomainModel)
	{
		// TODO from Template: customize this duplicate message for the business key your API should expose.
		return "The version with version `" + versionDomainModel.version() + "` already exists";
	}
}