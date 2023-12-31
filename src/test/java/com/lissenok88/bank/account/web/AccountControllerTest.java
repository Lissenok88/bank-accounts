package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.model.AccountMapper;
import com.lissenok88.bank.account.repository.AccountRepository;
import com.lissenok88.bank.account.to.AccountTo;
import com.lissenok88.bank.account.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.lissenok88.bank.account.util.JsonUtil.writeValue;
import static com.lissenok88.bank.account.web.AccountTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AccountController.REST_URL;
    private static final String PIN_NUMBER = "3333";
    private static final String NAME_PIN = "pin";

    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public AccountMapper accountMapper;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ACCOUNT_TO_MATCHER.contentJson(ACCOUNT_TO_1, ACCOUNT_TO_2));

    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + ACCOUNT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ACCOUNT_TO_MATCHER.contentJson(ACCOUNT_TO_1));
    }

    @Test
    void update() throws Exception {
        AccountTo updated = getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + "/" + ACCOUNT_ID)
                .param(NAME_PIN, "5555")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ACCOUNT_TO_MATCHER.assertMatch(accountMapper.toTo(accountRepository.getExisted(ACCOUNT_ID)), getUpdate());
    }

    @Test
    @Transactional
    void createWithLocation() throws Exception {
        AccountTo newTo = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param(NAME_PIN, PIN_NUMBER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(newTo)))
                .andExpect(status().isCreated());

        Account created = ACCOUNT_MATCHER.readFromJson(action);
        long newId = created.id();
        Account newAccount = new Account(newId, newTo.getName(), newTo.getBalance(), PIN_NUMBER);
        ACCOUNT_MATCHER.assertMatch(created, newAccount);
        ACCOUNT_MATCHER.assertMatch(accountRepository.getExisted(created.id()), newAccount);
    }
}