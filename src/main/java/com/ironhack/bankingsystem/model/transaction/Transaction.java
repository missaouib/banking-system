package com.ironhack.bankingsystem.model.transaction;

import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.utils.Money;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate creationDate;
    private Money amount;
    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    private Account origin;
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destination;

    public Transaction() {
    }

    public Transaction(LocalDate creationDate, Money amount, Account origin, Account destination) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
