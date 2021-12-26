package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.DocumentNotes;

public interface DocumentNotesRepository extends JpaRepository<DocumentNotes, Long> {
}
