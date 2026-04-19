package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VersionDomainPersistenceAdapterJpaRepository
		extends JpaRepository<VersionDomainPersistenceAdapterModel, UUID>
{
	boolean existsByDomainID(final Long domainID);

	Optional<VersionDomainPersistenceAdapterModel> findOneByDomainID(Long domainID);

	Optional<VersionDomainPersistenceAdapterModel> findOneByPersistenceID(UUID persistenceID);

	Collection<VersionDomainPersistenceAdapterModel> findAllByDomainIDIn(Collection<Long> domainIDs);

	@Query("""
			SELECT m.domainID
			FROM VersionDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			""")
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);
}