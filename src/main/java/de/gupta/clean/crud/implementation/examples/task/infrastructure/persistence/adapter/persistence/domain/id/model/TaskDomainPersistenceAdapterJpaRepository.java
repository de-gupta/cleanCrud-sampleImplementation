package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskDomainPersistenceAdapterJpaRepository
		extends JpaRepository<TaskDomainPersistenceAdapterModel, UUID>
{
	boolean existsByDomainID(final Long domainID);

	Optional<TaskDomainPersistenceAdapterModel> findOneByDomainID(Long domainID);

	Optional<TaskDomainPersistenceAdapterModel> findOneByPersistenceID(UUID persistenceID);

	@Query("""
			SELECT m.domainID
			FROM TaskDomainPersistenceAdapterModel m
			WHERE m.domainID IN :domainIDs
			"""
	)
	Collection<Long> findExistingDomainIDsFrom(@Param("domainIDs") final Collection<Long> domainIDs);
}