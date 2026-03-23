package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.repository;

import de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.model.TaskPersistenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskPersistenceModelImpl, UUID>
{
	default Collection<TaskPersistenceModel> findAllAsPersistenceModels()
	{
		return findAll().stream().map(TaskPersistenceModel.class::cast).toList();
	}

	@Query("SELECT t.title FROM TaskPersistenceModelImpl t WHERE t.title IN :titles")
	List<String> findTitlesByTitleIn(@Param("titles") final Collection<String> titles);
}