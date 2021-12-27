package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.DocumentNotes;

import java.util.List;

public interface DocumentNotesRepository extends JpaRepository<DocumentNotes, Long> {

    List<DocumentNotes> findAllByDocumentId(Long id);

}
