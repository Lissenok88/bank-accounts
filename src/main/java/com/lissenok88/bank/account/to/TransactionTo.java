package com.lissenok88.bank.account.to;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

@Data
@Value
@ToString
public class TransactionTo extends BaseTo {

    @NotBlank
    long idAccount;

    @NotBlank
    long sum;

    @NotBlank
    String operation;

    public TransactionTo(Long id, long idAccount, long sum, String operation) {
        super(id);
        this.idAccount = idAccount;
        this.sum = sum;
        this.operation = operation;
    }
}
