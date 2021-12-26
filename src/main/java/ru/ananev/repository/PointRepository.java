package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
}
