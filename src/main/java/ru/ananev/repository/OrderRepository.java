package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
