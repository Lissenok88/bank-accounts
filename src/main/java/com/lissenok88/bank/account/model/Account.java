package com.lissenok88.bank.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String name;

    @Column(name = "balance", nullable = false)
    private long balance;

    @Column(name = "pin", nullable = false)
    private int pin;

    public Account(long id, String name, Long balance, int pin) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }
}
