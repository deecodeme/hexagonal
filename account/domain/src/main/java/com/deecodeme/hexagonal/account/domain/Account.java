package com.deecodeme.hexagonal.account.domain;

import com.deecodeme.hexagonal.ddd.Entity;
import com.deecodeme.hexagonal.ddd.ValueObject;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Account extends Entity {
    private final AccountId accountId;
    private final AccountName name;
    private final AccountEmail email;

    public static Account of(AccountName name, AccountEmail email) {
        return new Account(AccountId.newId(), name, email);
    }

    public static Account of(AccountId accountId, AccountName name, AccountEmail email) {
        return new Account(accountId, name, email);
    }

    private Account(AccountId accountId, AccountName name, AccountEmail email) {
        super(accountId.id);
        this.accountId = accountId;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(accountId, account.accountId)) return false;
        if (!Objects.equals(name, account.name)) return false;
        return Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public static class AccountId implements ValueObject {
        private final String id;

        public static AccountId newId() {
            return new AccountId(UUID.randomUUID().toString());
        }

        public static AccountId of(String id) {
            return new AccountId(id);
        }

        private AccountId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AccountId)) return false;
            AccountId accountId = (AccountId) o;
            return id.equals(accountId.id);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

    public static class AccountName implements ValueObject {
        private final String name;

        public static AccountName of(String name) {
            return new AccountName(name);
        }

        private AccountName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AccountName)) return false;
            AccountName accountName = (AccountName) o;
            return name.equals(accountName.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    public static class AccountEmail implements ValueObject {
        private final String email;

        public static AccountEmail of(String email) {
            return new AccountEmail(email);
        }

        private AccountEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AccountEmail)) return false;
            AccountEmail accountEmail = (AccountEmail) o;
            return email.equals(accountEmail.email);
        }

        @Override
        public int hashCode() {
            return email.hashCode();
        }
    }
}
