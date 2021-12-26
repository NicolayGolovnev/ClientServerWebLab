package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.CompanyPark;

public interface CompanyParkRepository extends JpaRepository<CompanyPark, Long> {
}
