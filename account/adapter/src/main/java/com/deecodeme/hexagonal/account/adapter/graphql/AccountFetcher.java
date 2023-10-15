package com.deecodeme.hexagonal.account.adapter.graphql;

import com.deecodeme.hexagonal.account.adapter.graphql.generated.types.AccountQuery;
import com.deecodeme.hexagonal.account.adapter.graphql.generated.types.AccountResponse;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

public interface AccountFetcher {
    AccountResponse accountById(@InputArgument String accountId);
    List<AccountResponse> accountsFilter(@InputArgument AccountQuery input);
}
