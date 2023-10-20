package com.lissenok88.bank.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @NotBlank
    @Column(name = "id_account", nullable = false)
    long idAccount;

    @NotBlank
    @Column(name = "sum", nullable = false)
    long sum;

    @NotBlank
    @Column(name = "operation", nullable = false)
    String operation;

    public Transaction(Long id, long idAccount, long sum, String operation) {
        super(id);
        this.idAccount = idAccount;
        this.sum = sum;
        this.operation = operation;
    }
}
