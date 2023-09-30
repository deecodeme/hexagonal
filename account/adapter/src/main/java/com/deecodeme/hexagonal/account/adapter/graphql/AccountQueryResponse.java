package com.deecodeme.hexagonal.account.adapter.graphql;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountQueryResponse {
    private final String id;
    private final String name;
    private final String email;

    public AccountQueryResponse(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

