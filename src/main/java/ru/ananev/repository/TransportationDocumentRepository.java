package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.TransportationDocument;

public interface TransportationDocumentRepository extends JpaRepository<TransportationDocument, Long> {
}
