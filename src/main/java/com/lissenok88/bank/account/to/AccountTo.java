package com.lissenok88.bank.account.to;


import lombok.Data;

@Data
public class AccountTo extends BaseTo {
    private String name;
    private long balance;
    private int pin;

    public AccountTo(long id, String name, long balance, int pin) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }
}
