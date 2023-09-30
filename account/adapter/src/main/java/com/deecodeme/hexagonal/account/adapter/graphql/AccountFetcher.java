package com.deecodeme.hexagonal.account.adapter.graphql;

import com.netflix.graphql.dgs.InputArgument;

public interface AccountFetcher {
    AccountQueryResponse account(@InputArgument String accountId);
}
