package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model.TagDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model.TagDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.domain.model.builder.BuilderFactories;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.AbstractDomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.adapter.DomainPersistenceIDManagement;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import de.gupta.clean.crud.template.infrastructure.persistence.history.repository.TriTemporalHistoryRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.history.service.AuditActorSupplier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
final class TagDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, TagDomainPersistenceAdapterModel,
		TagDomainPersistenceAdapterHistoryModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	TagDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, TagDomainPersistenceAdapterModel> repository,
			final TriTemporalHistoryRepository<Long, TagDomainPersistenceAdapterHistoryModel> historyRepository,
			@Qualifier("tagLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator,
			@Qualifier("tagAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(repository,
				BuilderFactories.of(TagDomainPersistenceAdapterModel::builder),
				historyRepository,
				BuilderFactories.of(TagDomainPersistenceAdapterHistoryModel::builder),
				domainIDGenerator, auditActorSupplier);
	}
}
