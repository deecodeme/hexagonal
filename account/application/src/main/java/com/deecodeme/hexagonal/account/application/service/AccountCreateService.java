package com.deecodeme.hexagonal.account.application.service;

import com.deecodeme.hexagonal.account.application.port.in.CreateAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.deecodeme.hexagonal.account.domain.AccountFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountCreateService implements CreateAccountUseCase {
    @Override
    public Account process(CreateAccountCommand command) {
        return AccountFactory.of(Account.AccountName.of(command.getAccountName()),
                Account.AccountEmail.of(command.getEmailAddress()));
    }
}
