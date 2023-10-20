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
    String name;

    long balance;

    @NotBlank
    String pin;

    public AccountTo(Long id, String name, long balance, String pin) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }
}
