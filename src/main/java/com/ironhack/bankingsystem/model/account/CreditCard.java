package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard  extends Account {


    private BigDecimal creditLimit;
    private BigDecimal interestRate;


    public CreditCard() {
    }

    public CreditCard(BigDecimal creditLimit, BigDecimal interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Status status, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }


    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
