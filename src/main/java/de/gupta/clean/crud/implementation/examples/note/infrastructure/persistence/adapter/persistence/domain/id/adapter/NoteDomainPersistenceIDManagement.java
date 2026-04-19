package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.adapter;

import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model.NoteDomainPersistenceAdapterHistoryModel;
import de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model.NoteDomainPersistenceAdapterModel;
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
final class NoteDomainPersistenceIDManagement
		extends AbstractDomainPersistenceIDManagement<Long, UUID, NoteDomainPersistenceAdapterModel,
		NoteDomainPersistenceAdapterHistoryModel>
		implements DomainPersistenceIDManagement<Long, UUID>
{
	NoteDomainPersistenceIDManagement(
			final DomainPersistenceAdapterRepository<Long, UUID, NoteDomainPersistenceAdapterModel> repository,
			final TriTemporalHistoryRepository<Long, NoteDomainPersistenceAdapterHistoryModel> historyRepository,
			@Qualifier("noteLongDomainIDGenerator") final DomainIDGenerator<Long> domainIDGenerator,
			@Qualifier("noteAuditActorSupplier") final AuditActorSupplier auditActorSupplier)
	{
		super(repository,
				BuilderFactories.of(NoteDomainPersistenceAdapterModel::builder),
				historyRepository,
				BuilderFactories.of(NoteDomainPersistenceAdapterHistoryModel::builder),
				domainIDGenerator, auditActorSupplier);
	}
}
