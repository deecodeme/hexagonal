package com.deecodeme.hexagonal.account.adapter.graphql;

import com.deecodeme.hexagonal.account.application.port.in.GetAccountUseCase;
import com.deecodeme.hexagonal.account.application.port.in.SearchAccountUseCase;
import com.deecodeme.hexagonal.account.domain.Account;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {DgsAutoConfiguration.class, AccountFetcherImpl.class})
class AccountFetcherImplTest {
    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private GetAccountUseCase getAccountUseCase;

    @MockBean
    private SearchAccountUseCase searchAccountUseCase;

    @Autowired
    private AccountFetcher accountFetcher;

    @Test
    void account() {
        //given
        given(getAccountUseCase.getAccount(any(GetAccountUseCase.GetAccountQuery.class)))
                .willReturn(
                        Account.of(Account.AccountName.of("TestName"), Account.AccountEmail.of("TestEmail"))
                );

        //when
        String name = dgsQueryExecutor.executeAndExtractJsonPath("{accountById(id: \"1\") {name}}", "data.accountById.name");

        //then
        then(name).isEqualTo("TestName");
    }

    @Test
    void searchAccountsShouldReturnListOfAccounts() {
        //given
        given(searchAccountUseCase.getAccount(any(SearchAccountUseCase.SearchAccountQuery.class)))
                .willReturn(
                        List.of(
                                Account.of(Account.AccountName.of("TestName1"), Account.AccountEmail.of("TestEmail2")),
                                Account.of(Account.AccountName.of("TestName2"), Account.AccountEmail.of("TestEmail2"))
                        )
                );

        //when
        List<String> names = dgsQueryExecutor.executeAndExtractJsonPath("{accountsFilter(accountQuery: {name: \"AnyName\"}) {name}}",
                "data.accountsFilter[*].name");

        //then
        then(names).containsExactly("TestName1", "TestName2");

    }

//    @Test
//    void searchAccountsShouldReturnListOfAccountUsingGeneratedCode() {
//        //given
//        given(searchAccountUseCase.getAccount(any(SearchAccountUseCase.SearchAccountQuery.class)))
//                .willReturn(
//                        List.of(
//                                Account.of(Account.AccountName.of("TestName1"), Account.AccountEmail.of("TestEmail2")),
//                                Account.of(Account.AccountName.of("TestName2"), Account.AccountEmail.of("TestEmail2"))
//                        )
//                );
//
//        //when
//        GraphQLQueryRequest graphQLQueryRequest = new GraphQLQueryRequest(
//                AccountsFilterGraphQLQuery.newRequest().accountQuery(
//                        AccountQuery.newBuilder().name("AnyName").build()).build(),
//                new AccountsFilterProjectionRoot().name());
//        LinkedHashMap<String, Map<String, String>> result = dgsQueryExecutor.execute(graphQLQueryRequest.serialize()).getData();
//        List<Map<String, String>> names = result.get("accountsFilter");
//
//
//        //then
//        then(names).containsExactly("TestName1", "TestName2");
//
//    }

}