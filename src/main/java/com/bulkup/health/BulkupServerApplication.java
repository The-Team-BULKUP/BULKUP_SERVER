package com.bulkup.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BulkupServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulkupServerApplication.class, args);
    }

}
