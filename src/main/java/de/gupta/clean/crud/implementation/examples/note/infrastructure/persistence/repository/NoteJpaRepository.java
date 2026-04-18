package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface NoteJpaRepository extends JpaRepository<NotePersistenceModelImpl, UUID>
{
	boolean existsByNote(final String note);

	@Query("SELECT t.note FROM NotePersistenceModelImpl t WHERE t.note IN :notes")
	List<String> findNotesByNoteIn(@Param("notes") final Collection<String> notes);

}