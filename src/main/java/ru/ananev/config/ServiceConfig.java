package ru.ananev.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value = "ru.ananev.repository")
@EntityScan(value = "ru.golovnev.entity")
public class ServiceConfig {
}
