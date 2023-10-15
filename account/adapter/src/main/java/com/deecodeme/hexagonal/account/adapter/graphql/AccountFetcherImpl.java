package com.deecodeme.hexagonal.account.adapter.graphql;

import com.deecodeme.hexagonal.account.adapter.graphql.generated.types.AccountQuery;
import com.deecodeme.hexagonal.account.adapter.graphql.generated.types.AccountResponse;
import com.deecodeme.hexagonal.account.application.port.in.GetAccountUseCase;
import com.deecodeme.hexagonal.account.application.port.in.SearchAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

@DgsComponent
public class AccountFetcherImpl implements AccountFetcher{
    private final GetAccountUseCase getAccountUseCase;

    private final SearchAccountUseCase searchAccountUseCase;

    @Autowired
    public AccountFetcherImpl(GetAccountUseCase getAccountUseCase, SearchAccountUseCase searchAccountUseCase) {
        this.getAccountUseCase = getAccountUseCase;
        this.searchAccountUseCase = searchAccountUseCase;
    }

    @DgsQuery
    public AccountResponse accountById(@InputArgument String accountId) {
        Account account = getAccountUseCase.getAccount(
                GetAccountUseCase.GetAccountQuery.builder()
                        .accountId(Account.AccountId.of(accountId))
                        .build());
        return mapAccountToAccountQueryResponse.apply(account);
    }

    @DgsQuery
    public List<AccountResponse> accountsFilter(@InputArgument AccountQuery accountQuery){
        List<Account> accounts = searchAccountUseCase.getAccount(
                SearchAccountUseCase.SearchAccountQuery.builder()
                        .accountName(accountQuery.getName())
                        .build()
        );
        return accounts.stream().map(mapAccountToAccountQueryResponse).toList();
    }

    Function<Account, AccountResponse> mapAccountToAccountQueryResponse = account -> AccountResponse.newBuilder()
            .id(account.getAccountId().getId())
            .email(account.getEmail().getEmail())
            .name(account.getName().getName())
            .build();
}
