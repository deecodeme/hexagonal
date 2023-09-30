package com.deecodeme.hexagonal.account.application.port.out;

import com.deecodeme.hexagonal.account.domain.Account;

public interface SaveNewAccount {
    Account save(Account account);
}
