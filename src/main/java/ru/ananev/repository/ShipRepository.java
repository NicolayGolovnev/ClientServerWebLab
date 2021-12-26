package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Ship;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}
