package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.adapter;

import de.gupta.clean.crud.implementation.examples.version.domain.model.dto.VersionDomainModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.model.APIToDomainUpdateAdapter;
import org.springframework.stereotype.Component;


@Component
final class VersionAPIToDomainUpdateAdapter
		implements APIToDomainUpdateAdapter<VersionAPIModelUpdatePatch, VersionDomainModelUpdatePatch>
{

	@Override
	public VersionDomainModelUpdatePatch mapToDomainModelUpdatePatch(final VersionAPIModelUpdatePatch apiModel)
	{
		return new VersionDomainModelUpdatePatch(
				apiModel.version()
		);
	}

	VersionAPIToDomainUpdateAdapter()
	{
	}
}