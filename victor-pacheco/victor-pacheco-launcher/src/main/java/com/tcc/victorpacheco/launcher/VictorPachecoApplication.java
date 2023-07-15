package com.tcc.victorpacheco.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.tcc.victorpacheco.infrastructure.repository")
@EntityScan("com.tcc.victorpacheco.infrastructure.entity")
@ComponentScan(value = "com.tcc.victorpacheco")
public class VictorPachecoApplication {
    public static void main(String[] args) {
        SpringApplication.run(VictorPachecoApplication.class, args);
    }
}
