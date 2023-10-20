package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.to.AccountTo;

public class AccountTestData {
    public static final MatcherFactory.Matcher<AccountTo> ACCOUNT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(AccountTo.class);
    public static final MatcherFactory.Matcher<Account> ACCOUNT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Account.class);


    public static final long ACCOUNT_ID = 1L;

    public static final Account ACCOUNT1 = new Account(ACCOUNT_ID, "Lyudmila", 155L, "5555");
    public static final Account ACCOUNT2 = new Account(ACCOUNT_ID + 1, "Elena", 250L, "4444");
    public static final AccountTo ACCOUNT_TO_1 = new AccountTo(ACCOUNT_ID, "Lyudmila", 155L);
    public static final AccountTo ACCOUNT_TO_2 = new AccountTo(ACCOUNT_ID + 1, "Elena", 250L);


    public static AccountTo getNew() {
        return new AccountTo(null,"Ketty", 0L);
    }

    public static AccountTo getUpdate() {
        return new AccountTo(ACCOUNT_ID,"Lyudmilka", ACCOUNT_TO_1.getBalance());
    }

}
