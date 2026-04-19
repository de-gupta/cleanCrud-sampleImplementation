package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.AbstractDomainPersistenceAdapterJpaRepository;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.repository.DomainPersistenceAdapterRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
@Qualifier("tagDomainPersistenceAdapterRepository")
final class TagDomainPersistenceAdapterRepository
		extends AbstractDomainPersistenceAdapterJpaRepository<Long, UUID, TagDomainPersistenceAdapterModel>
		implements DomainPersistenceAdapterRepository<Long, UUID, TagDomainPersistenceAdapterModel>
{
	private final TagDomainPersistenceAdapterJpaRepository jpaRepository;

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
	protected Optional<TagDomainPersistenceAdapterModel> findOneByPersistenceID(final UUID uuid)
	{
		return jpaRepository.findOneByPersistenceID(uuid);
	}

	@Override
	protected Optional<TagDomainPersistenceAdapterModel> findOneByDomainID(final Long domainID)
	{
		return jpaRepository.findOneByDomainID(domainID);
	}

	@Override
	protected Collection<TagDomainPersistenceAdapterModel> findAllByDomainIDIn(final Collection<Long> domainIDs)
	{
		return jpaRepository.findAllByDomainIDIn(domainIDs);
	}

	TagDomainPersistenceAdapterRepository(final TagDomainPersistenceAdapterJpaRepository jpaRepository)
	{
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
}