package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByPassport(String passport);

}
