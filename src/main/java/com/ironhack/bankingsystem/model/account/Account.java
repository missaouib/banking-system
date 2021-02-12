package com.ironhack.bankingsystem.model.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.AccountNoOwnerByName;
import com.ironhack.bankingsystem.exceptions.FrozenAccount;
import com.ironhack.bankingsystem.exceptions.UnauthorizedAccess;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.User;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private Money balance;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "primaryOwner_id")
    private AccountHolder primaryOwner;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "secondaryOwner_id")
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonBackReference
    @OneToMany(mappedBy = "origin")
    private List<Transaction> originTransactions;
    @JsonBackReference
    @OneToMany(mappedBy = "destination")
    private List<Transaction> destinationTransactions;


    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Status status) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        setPenaltyFee(new BigDecimal(40));
        this.creationDate = LocalDate.now();
        this.status = status;
    }

    /**
     * method to check if the account belongs to the owner
     */

    public void isOwnedBy(String name) {
        if (!primaryOwner.getUsername().equals(name) && (secondaryOwner == null || !secondaryOwner.getUsername().equals(name))) {
            throw new AccountNoOwnerByName();
        }
    }

    /**
     * method to modify the account status
     */

    public void toFrozen() {
        setStatus(Status.FROZEN);
    }

    /**
     * method to check the account status
     */
    public void checkFrozen() {
        if (status == Status.FROZEN) {
            throw new FrozenAccount();
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Transaction> getOriginTransactions() {
        return originTransactions;
    }

    public void setOriginTransactions(List<Transaction> originTransactions) {
        this.originTransactions = originTransactions;
    }

    public List<Transaction> getDestinationTransactions() {
        return destinationTransactions;
    }

    public void setDestinationTransactions(List<Transaction> destinationTransactions) {
        this.destinationTransactions = destinationTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && balance.equals(account.balance) && primaryOwner.equals(account.primaryOwner) && secondaryOwner.equals(account.secondaryOwner) && penaltyFee.equals(account.penaltyFee) && creationDate.equals(account.creationDate) && status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, primaryOwner, secondaryOwner, penaltyFee, creationDate, status);
    }
}
