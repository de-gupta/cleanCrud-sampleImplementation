package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskVersionDomainPersistenceAdapterJpaRepository
		extends JpaRepository<TaskVersionDomainPersistenceAdapterModel, UUID>
{
	boolean existsByDomainID(final Long domainID);

	Optional<TaskVersionDomainPersistenceAdapterModel> findOneByDomainID(Long domainID);

	Optional<TaskVersionDomainPersistenceAdapterModel> findOneByPersistenceID(UUID persistenceID);

	Collection<TaskVersionDomainPersistenceAdapterModel> findAllByDomainIDIn(Collection<Long> domainIDs);

	@Query("""
			SELECT m.domainID
			FROM TaskVersionDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			""")
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);
}