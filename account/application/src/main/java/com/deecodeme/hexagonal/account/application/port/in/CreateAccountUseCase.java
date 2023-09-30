package com.deecodeme.hexagonal.account.application.port.in;

import com.deecodeme.hexagonal.account.domain.Account;

public interface CreateAccountUseCase {
    Account process(CreateAccountCommand command);

    class CreateAccountCommand {
        private final String accountName;
        private final String emailAddress;

        public CreateAccountCommand(String accountName, String emailAddress) {
            this.accountName = accountName;
            this.emailAddress = emailAddress;
        }

        public String getAccountName() {
            return accountName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }
    }
}
