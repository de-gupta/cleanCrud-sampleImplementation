package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("versionDomainPersistenceAdapterModelBuilderFactory")
final class VersionDomainPersistenceAdapterModelBuilderFactory
		implements
		ModelBuilderFactory<DomainPersistenceAdapterModel<Long, UUID>, DomainPersistenceAdapterModel.Builder<Long,
				UUID, VersionDomainPersistenceAdapterModel>>
{
	@Override
	public DomainPersistenceAdapterModel.Builder<Long, UUID, VersionDomainPersistenceAdapterModel> builder()
	{
		return VersionDomainPersistenceAdapterModel.builder();
	}
}