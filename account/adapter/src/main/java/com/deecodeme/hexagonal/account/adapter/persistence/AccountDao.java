package com.deecodeme.hexagonal.account.adapter.persistence;

import com.deecodeme.hexagonal.account.application.port.out.SaveNewAccount;
import com.deecodeme.hexagonal.account.application.port.out.UpdateAccountState;
import com.deecodeme.hexagonal.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDao implements SaveNewAccount, UpdateAccountState {

    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return AccountMongoEntityMapper.mapToDomainEntity.apply(
                accountRepository.save(AccountMongoEntityMapper.mapToMongoEntity.apply(account))
        );
    }
}
