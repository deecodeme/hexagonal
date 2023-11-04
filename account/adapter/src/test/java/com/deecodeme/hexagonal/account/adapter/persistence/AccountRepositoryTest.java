package com.deecodeme.hexagonal.account.adapter.persistence;

import config.MongoDBTestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.BDDAssertions.then;

@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = {MongoDBTestContainerConfig.class})
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldSaveAccount() {
        // given
        String name = "John Doe";
        String email = "a@b.com";

        // when
        this.accountRepository.findByEmail(email).ifPresent(account -> this.accountRepository.delete(account));
        this.accountRepository.save(
                AccountMongoEntity.builder()
                        .name(name)
                        .email(email)
                        .build()
        );

        // then
        then(this.accountRepository.findByEmail(email)).isNotEmpty().get()
                .satisfies(account -> {
                    then(account.getName()).isEqualTo(name);
                    then(account.getEmail()).isEqualTo(email);
                });
    }
}