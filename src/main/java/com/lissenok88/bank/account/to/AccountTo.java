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

    Long balance;

    public AccountTo(Long id, String name, Long balance) {
        super(id);
        this.name = name;
        this.balance = balance;
    }
}
