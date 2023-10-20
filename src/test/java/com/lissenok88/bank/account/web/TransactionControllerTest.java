package com.lissenok88.bank.account.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionControllerTest extends AbstractControllerTest {

    private static final String REST_URL = TransactionController.REST_URL;
    private static final String DEPOSIT = "/deposit";
    private static final String WITHDRAW = "/withdraw";
    private static final String TRANSFER = "/transfer-to-other-account";
    private static final String AMOUNT = "amount";
    private static final String ID_ACCOUNT = "idAccount";
    private static final String PIN = "pin";
    private static final String ID_OTHER_ACCOUNT = "idOtherAccount";
    private static final String AMOUNT_NUMBER = "100";
    private static final String PIN_NUMBER = "5555";



    @Test
    void deposit() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + DEPOSIT)
                .param(AMOUNT, AMOUNT_NUMBER)
                .param(ID_ACCOUNT, String.valueOf(AccountTestData.ACCOUNT_ID))
                .param(PIN, PIN_NUMBER))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void withdraw() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + WITHDRAW)
                .param(AMOUNT, AMOUNT_NUMBER)
                .param(ID_ACCOUNT, String.valueOf(AccountTestData.ACCOUNT_ID))
                .param(PIN, PIN_NUMBER))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void transferToOtherAccount() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + TRANSFER)
                .param(AMOUNT, AMOUNT_NUMBER)
                .param(ID_ACCOUNT, String.valueOf(AccountTestData.ACCOUNT_ID))
                .param(PIN, PIN_NUMBER)
                .param(ID_OTHER_ACCOUNT, String.valueOf(AccountTestData.ACCOUNT_ID + 1)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}