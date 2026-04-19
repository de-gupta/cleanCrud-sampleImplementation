package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagDomainPersistenceAdapterJpaRepository
		extends JpaRepository<TagDomainPersistenceAdapterModel, UUID>
{
	boolean existsByDomainID(final Long domainID);

	Optional<TagDomainPersistenceAdapterModel> findOneByDomainID(Long domainID);

	Optional<TagDomainPersistenceAdapterModel> findOneByPersistenceID(UUID persistenceID);

	Collection<TagDomainPersistenceAdapterModel> findAllByDomainIDIn(Collection<Long> domainIDs);

	@Query("""
			SELECT m.domainID
			FROM TagDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			""")
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);
}