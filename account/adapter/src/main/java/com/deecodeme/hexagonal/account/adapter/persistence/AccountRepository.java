package com.deecodeme.hexagonal.account.adapter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<AccountMongoEntity, String> {
    public Optional<AccountMongoEntity> findByEmail(String email);
}
