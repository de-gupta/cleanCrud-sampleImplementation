package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface TaskDomainPersistenceAdapterJpaRepository
		extends JpaRepository<TaskDomainPersistenceAdapterModel, Long>
{
	boolean existsByDomainID(final Long domainID);

	@Query("""
			SELECT m.domainID
			FROM TaskDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			"""
	)
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);

	Collection<TaskDomainPersistenceAdapterModel> findAllByDomainIDAndValidFromIsBeforeAndValidToIsAfter(
			final Long domainID, final Instant validFrom, final Instant validTo);

	Collection<TaskDomainPersistenceAdapterModel> findAllByPersistenceIDAndValidFromIsBeforeAndValidToIsAfter(
			final UUID persistenceID, final Instant validFrom, final Instant validTo);
}