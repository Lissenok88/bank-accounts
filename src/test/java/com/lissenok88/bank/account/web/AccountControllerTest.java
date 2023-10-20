package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.repository.AccountRepository;
import com.lissenok88.bank.account.to.AccountTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.lissenok88.bank.account.util.JsonUtil.writeValue;
import static com.lissenok88.bank.account.web.AccountTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AccountController.REST_URL;

    @Autowired
    public AccountRepository accountRepository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ACCOUNT_TO_MATCHER.contentJson(ACCOUNT1, ACCOUNT2));

    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + ACCOUNT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ACCOUNT_TO_MATCHER.contentJson(ACCOUNT1));
    }

    @Test
    void createWithLocation() throws Exception {
        AccountTo newTo = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newTo)))
                .andExpect(status().isCreated());

        Account created = ACCOUNT_MATCHER.readFromJson(action);
        long newId = created.id();
        Account newAccount = new Account(newId, newTo.getName(), newTo.getBalance(), newTo.getPin());
        ACCOUNT_MATCHER.assertMatch(created, newAccount);
        ACCOUNT_MATCHER.assertMatch(accountRepository.getExisted(created.id()), newAccount);
    }
}