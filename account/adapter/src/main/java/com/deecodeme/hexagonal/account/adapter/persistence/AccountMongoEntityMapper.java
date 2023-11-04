package com.deecodeme.hexagonal.account.adapter.persistence;

import com.deecodeme.hexagonal.account.domain.Account;
import com.deecodeme.hexagonal.account.domain.AccountFactory;

import java.util.function.Function;

public class AccountMongoEntityMapper {
    public static Function<Account, AccountMongoEntity> mapToMongoEntity = account -> AccountMongoEntity.builder()
            .accountId(account.getAccountId().getId())
            .name(account.getName().getName())
            .email(account.getEmail().getEmail())
            .build();

    public static Function<AccountMongoEntity, Account> mapToDomainEntity = accountMongoEntity -> AccountFactory.of(
            Account.AccountId.of(accountMongoEntity.getAccountId()),
            Account.AccountName.of(accountMongoEntity.getName()),
            Account.AccountEmail.of(accountMongoEntity.getEmail())
    );
}
