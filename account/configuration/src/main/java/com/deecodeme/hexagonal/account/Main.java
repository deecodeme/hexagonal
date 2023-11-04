package com.deecodeme.hexagonal.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.deecodeme.hexagonal.account.application",
        "com.deecodeme.hexagonal.account.adapter",
        "com.deecodeme.hexagonal.account.domain"})
@EnableMongoRepositories(basePackages = "com.deecodeme.hexagonal.account.adapter.out.persistence")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}