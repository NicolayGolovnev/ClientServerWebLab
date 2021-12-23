package ru.ananev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ananev.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
