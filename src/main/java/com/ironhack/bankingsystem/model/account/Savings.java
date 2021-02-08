package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.IncorrectInterestRate;
import com.ironhack.bankingsystem.exceptions.IncorrectMinimumBalance;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings  extends Account {

    private String secretKey;
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;


    public Savings() {
    }

    public Savings(String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, Status.ACTIVE);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner,  Status.ACTIVE);
        this.secretKey = secretKey;
        setMinimumBalance(new BigDecimal(1000));
        setInterestRate(new BigDecimal(0.0025).setScale(4, RoundingMode.HALF_EVEN));
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
