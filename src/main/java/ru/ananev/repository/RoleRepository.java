package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
