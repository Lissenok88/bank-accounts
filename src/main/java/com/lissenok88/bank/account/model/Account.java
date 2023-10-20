package com.lissenok88.bank.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "balance", nullable = false)
    @NotBlank
    private Long balance;

    @Column(name = "pin", nullable = false)
    @NotBlank
    private String pin;

    public Account(Long id, String name, Long balance, String pin) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }
}
