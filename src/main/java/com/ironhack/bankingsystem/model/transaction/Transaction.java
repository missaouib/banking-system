package com.ironhack.bankingsystem.model.transaction;

import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.utils.Money;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime creationDate;
    private Money amount;
    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    private Account origin;
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    public Transaction() {
    }

    public Transaction(LocalDateTime creationDate, Money amount, Account origin, Account destination) {
        this.creationDate = creationDate;
        this.amount = amount;
        this.origin = origin;
        this.destination = destination;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Money getAmount() {
        return amount;
    }
}
