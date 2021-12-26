package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
