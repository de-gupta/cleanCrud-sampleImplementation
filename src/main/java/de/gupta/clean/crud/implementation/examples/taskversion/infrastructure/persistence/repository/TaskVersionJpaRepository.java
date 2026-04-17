package de.gupta.clean.crud.implementation.examples.taskversion.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskVersionJpaRepository extends JpaRepository<TaskVersionPersistenceModelImpl, UUID>
{
	boolean existsByVersion(final long version);

	@Query("SELECT t.version FROM TaskVersionPersistenceModelImpl t WHERE t.version IN :versions")
	List<Long> findVersionsByVersionIn(@Param("versions") final Collection<Long> versions);

}
