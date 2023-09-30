package com.deecodeme.hexagonal.account.domain;

public class AccountFactory {
    public static Account of(Account.AccountName name, Account.AccountEmail email){
        return Account.of(name, email);
    };
}
