package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Point;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findPointByLocation(String location);
}
