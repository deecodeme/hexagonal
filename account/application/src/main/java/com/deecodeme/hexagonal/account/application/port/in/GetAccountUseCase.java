package com.deecodeme.hexagonal.account.application.port.in;

import com.deecodeme.hexagonal.account.domain.Account;
import lombok.Builder;

public interface GetAccountUseCase {
    Account getAccount(GetAccountQuery query);

    @Builder
    class GetAccountQuery {
        private final Account.AccountId accountId;

        public static GetAccountQuery of(String accountId) {
            return new GetAccountQuery(Account.AccountId.of(accountId));
        }

        private GetAccountQuery(Account.AccountId accountId) {
            this.accountId = accountId;
        }

        public Account.AccountId getAccountId() {
            return accountId;
        }
    }
}
