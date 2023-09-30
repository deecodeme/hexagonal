package com.deecodeme.hexagonal.account.adapter.graphql;

import com.deecodeme.hexagonal.account.application.port.in.GetAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DgsAutoConfiguration.class, AccountFetcherImpl.class})
class AccountFetcherImplTest {
    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private GetAccountUseCase getAccountUseCase;

    @Autowired
    private AccountFetcher accountFetcher;

    @Test
    void account() {
        //given
        given(getAccountUseCase.getAccount(any(GetAccountUseCase.GetAccountQuery.class)))
                .willReturn(
                        Account.of(Account.AccountName.of("Test"), Account.AccountEmail.of("Test"))
                );

        //when
        String name = dgsQueryExecutor.executeAndExtractJsonPath("{account(id: \"1\") {name}}", "data.account.name");

        //then
        then(name).isEqualTo("Test");
    }

}