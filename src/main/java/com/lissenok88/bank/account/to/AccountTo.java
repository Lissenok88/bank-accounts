package com.lissenok88.bank.account.to;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

@Data
@Value
@ToString
public class AccountTo extends BaseTo {
    @NotBlank
    private String name;

    private long balance;
    @NotBlank
    private int pin;

    public AccountTo(long id, String name, long balance, int pin) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }
}
