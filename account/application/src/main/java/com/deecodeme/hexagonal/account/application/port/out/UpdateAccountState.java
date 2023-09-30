package com.deecodeme.hexagonal.account.application.port.out;

import com.deecodeme.hexagonal.account.domain.Account;

public interface UpdateAccountState {
    Account save(Account account);
}
