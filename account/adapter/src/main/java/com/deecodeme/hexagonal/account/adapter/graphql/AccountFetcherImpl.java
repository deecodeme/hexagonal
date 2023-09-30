package com.deecodeme.hexagonal.account.adapter.graphql;

import com.deecodeme.hexagonal.account.application.port.in.GetAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
@RequiredArgsConstructor
public class AccountFetcherImpl implements AccountFetcher{
    @Autowired
    private final GetAccountUseCase getAccountUseCase;

    @DgsQuery
    public AccountQueryResponse account(@InputArgument String accountId) {
        Account account = getAccountUseCase.getAccount(GetAccountUseCase.GetAccountQuery.of(accountId));
        return AccountQueryResponse.builder()
                .id(account.getAccountId().getId())
                .email(account.getEmail().getEmail())
                .name(account.getName().getName())
                .build();
    }
}
