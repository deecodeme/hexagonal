package com.deecodeme.hexagonal.account.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.deecodeme.hexagonal.account")
public class AccountAdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountAdapterApplication.class, args);
    }
}
