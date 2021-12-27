package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.SequenceRoute;

import java.util.List;

public interface SequenceRouteRepository extends JpaRepository<SequenceRoute, Long> {

    List<SequenceRoute> findAllByRouteId(Long id);

}
