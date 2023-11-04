package com.deecodeme.hexagonal.account.adapter.out.persistence;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "accounts")
@Builder
@Getter
public class AccountMongoEntity {
    @MongoId
    private String accountId;
    private String name;
    private String email;
}
