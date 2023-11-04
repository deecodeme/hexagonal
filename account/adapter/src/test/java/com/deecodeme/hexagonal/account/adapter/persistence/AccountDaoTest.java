package com.deecodeme.hexagonal.account.adapter.persistence;

import com.deecodeme.hexagonal.account.domain.Account;
import com.deecodeme.hexagonal.account.domain.AccountFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountDaoTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountDao saveNewAccount;

    @Test
    void shouldSaveAccount() {
        // given
        String name = "John Doe";
        String email = "a@b.com";
        Account account = AccountFactory.of(
                Account.AccountName.of(name),
                Account.AccountEmail.of(email)
        );
        AccountMongoEntity accountMongoEntity = AccountMongoEntityMapper.mapToMongoEntity.apply(account);

        // when
        when(this.accountRepository.save(any(AccountMongoEntity.class))).thenReturn(accountMongoEntity);
        Account savedAccount = this.saveNewAccount.save(account);

        // then
       then(savedAccount).hasSameHashCodeAs(account);
    }
}