package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteDomainPersistenceAdapterJpaRepository
		extends JpaRepository<NoteDomainPersistenceAdapterModel, UUID>
{
	boolean existsByDomainID(final Long domainID);

	Optional<NoteDomainPersistenceAdapterModel> findOneByDomainID(Long domainID);

	Optional<NoteDomainPersistenceAdapterModel> findOneByPersistenceID(UUID persistenceID);

	Collection<NoteDomainPersistenceAdapterModel> findAllByDomainIDIn(Collection<Long> domainIDs);

	@Query("""
			SELECT m.domainID
			FROM NoteDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			""")
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);
}