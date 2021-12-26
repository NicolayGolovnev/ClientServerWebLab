package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.PaymentNote;

public interface PaymentNoteRepository extends JpaRepository<PaymentNote, Long> {
}
