package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.PaymentNote;

import java.util.List;

public interface PaymentNoteRepository extends JpaRepository<PaymentNote, Long> {

    List<PaymentNote> findAllByOrderId(Long id);

}
