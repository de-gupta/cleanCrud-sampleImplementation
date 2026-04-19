package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Qualifier("tagDomainPersistenceAdapterModelBuilderFactory")
final class TagDomainPersistenceAdapterModelBuilderFactory
		implements
		ModelBuilderFactory<DomainPersistenceAdapterModel<Long, UUID>, DomainPersistenceAdapterModel.Builder<Long,
				UUID, TagDomainPersistenceAdapterModel>>
{
	@Override
	public DomainPersistenceAdapterModel.Builder<Long, UUID, TagDomainPersistenceAdapterModel> builder()
	{
		return TagDomainPersistenceAdapterModel.builder();
	}
}