package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.CompanyPark;

import java.util.Optional;

public interface CompanyParkRepository extends JpaRepository<CompanyPark, Long> {

    Optional<CompanyPark> findCompanyParkByName(String name);

}
