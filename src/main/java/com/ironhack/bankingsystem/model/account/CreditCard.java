package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.IncorrectCreditLimit;
import com.ironhack.bankingsystem.exceptions.IncorrectInterestRate;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, Status.ACTIVE);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, Status.ACTIVE);
        setCreditLimit(new BigDecimal(100));
        setInterestRate(new BigDecimal(0.2).setScale(1, RoundingMode.HALF_EVEN));
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
