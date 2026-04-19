package de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TagJpaRepository extends JpaRepository<TagPersistenceModelImpl, UUID>
{
	boolean existsByName(final String name);

	@Query("SELECT t.name FROM TagPersistenceModelImpl t WHERE t.name IN :names")
	List<String> findNamesByNameIn(@Param("names") final Collection<String> names);

}
