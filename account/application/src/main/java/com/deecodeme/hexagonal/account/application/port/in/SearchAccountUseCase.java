package com.deecodeme.hexagonal.account.application.port.in;

import com.deecodeme.hexagonal.account.domain.Account;
import lombok.Builder;

import java.util.List;

public interface SearchAccountUseCase {
    List<Account> getAccount(SearchAccountQuery query);

    @Builder
    class SearchAccountQuery {
        private final String accountName;

        private SearchAccountQuery(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountName() {
            return accountName;
        }
    }
}
