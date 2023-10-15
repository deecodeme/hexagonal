package com.deecodeme.hexagonal.account.application.service;

import com.deecodeme.hexagonal.account.application.port.in.GetAccountUseCase;
import com.deecodeme.hexagonal.account.application.port.in.SearchAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.deecodeme.hexagonal.account.domain.AccountFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountQueryService implements SearchAccountUseCase, GetAccountUseCase {
    @Override
    public List<Account> getAccount(SearchAccountQuery query) {
        return List.of(AccountFactory.of(Account.AccountName.of(query.getAccountName()),
                Account.AccountEmail.of("Email")));
    }

    @Override
    public Account getAccount(GetAccountQuery query) {
        return AccountFactory.of(Account.AccountName.of(query.getAccountId().getId()),
                Account.AccountEmail.of("Email"));
    }
}
