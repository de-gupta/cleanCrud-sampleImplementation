package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
@Qualifier("taskVersionDomainPersistenceAdapterRepository")
final class TaskVersionDomainPersistenceAdapterRepository
		extends AbstractDomainPersistenceAdapterJpaRepository<Long, UUID, TaskVersionDomainPersistenceAdapterModel>
		implements DomainPersistenceAdapterRepository<Long, UUID, TaskVersionDomainPersistenceAdapterModel>
{
	private final TaskVersionDomainPersistenceAdapterJpaRepository jpaRepository;

	@Override
	public boolean existsByDomainID(final Long domainID)
	{
		return jpaRepository.existsByDomainID(domainID);
	}

	@Override
	public Collection<Long> existingDomainIDsFrom(final Collection<Long> domainIDs)
	{
		return jpaRepository.findExistingDomainIDsFrom(domainIDs);
	}

	@Override
	protected Optional<TaskVersionDomainPersistenceAdapterModel> findOneByPersistenceID(final UUID uuid)
	{
		return jpaRepository.findOneByPersistenceID(uuid);
	}

	@Override
	protected Optional<TaskVersionDomainPersistenceAdapterModel> findOneByDomainID(final Long domainID)
	{
		return jpaRepository.findOneByDomainID(domainID);
	}

	@Override
	protected Collection<TaskVersionDomainPersistenceAdapterModel> findAllByDomainIDIn(final Collection<Long> domainIDs)
	{
		return jpaRepository.findAllByDomainIDIn(domainIDs);
	}

	TaskVersionDomainPersistenceAdapterRepository(final TaskVersionDomainPersistenceAdapterJpaRepository jpaRepository)
	{
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
}