package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.SequenceRoute;

public interface SequenceRouteRepository extends JpaRepository<SequenceRoute, Long> {
}
