package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.AccountNoOwnerByName;
import com.ironhack.bankingsystem.exceptions.FrozenAccount;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private Money balance;
    @ManyToOne
    @JoinColumn(name = "primaryOwner_id")
    private AccountHolder primaryOwner;
    @ManyToOne
    @JoinColumn(name = "secondaryOwner_id")
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;

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

    @OneToMany(mappedBy = "origin")
    private List<Transaction> originTransactions;
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

    /** method to check if the account belongs to the owner */

    public void isOwnedBy(String name) {
        if (!primaryOwner.getName().equals(name) && (secondaryOwner == null || !secondaryOwner.getName().equals(name))) {
            throw new AccountNoOwnerByName();
        }

    }

    /** method to modify the account status */

    public void toFrozen(){
        setStatus(Status.FROZEN);
    }

    /** method to check the account status */
    public void checkFrozen(){
        if(status == Status.FROZEN){
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
}
