package com.lissenok88.bank.account.web;

import com.lissenok88.bank.account.model.Account;
import com.lissenok88.bank.account.to.AccountTo;

public class AccountTestData {
    public static final MatcherFactory.Matcher<AccountTo> ACCOUNT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(AccountTo.class);
    public static final MatcherFactory.Matcher<Account> ACCOUNT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Account.class);


    public static final long ACCOUNT_ID = 1L;

    public static final AccountTo ACCOUNT1 = new AccountTo(ACCOUNT_ID, "Lyudmila", 155L, "5555");
    public static final AccountTo ACCOUNT2 = new AccountTo(ACCOUNT_ID + 1, "Elena", 250L, "4444");

    public static AccountTo getNew() {
        return new AccountTo(null,"Lyudmila", 250L,"3333");
    }

}
