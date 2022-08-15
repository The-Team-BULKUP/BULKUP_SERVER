package com.bulkup.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = {"com.bulkup.health.entity"})
@EnableJpaRepositories(basePackages = {"com.bulkup.health.repository"})
public class BulkupServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BulkupServerApplication.class, args);
    }
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
}
