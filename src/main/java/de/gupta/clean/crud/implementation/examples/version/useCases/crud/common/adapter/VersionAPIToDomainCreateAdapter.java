package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelCreate;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainCreateAdapter;
import org.springframework.stereotype.Component;


@Component
final class VersionAPIToDomainCreateAdapter
		implements APIToDomainCreateAdapter<VersionAPIModelCreate, VersionDomainModelCreate>
{

	@Override
	public VersionDomainModelCreate mapToDomainModelCreate(final VersionAPIModelCreate apiModel)
	{
		return new VersionDomainModelCreate(
				apiModel.version()
		);
	}

	VersionAPIToDomainCreateAdapter()
	{
	}
}