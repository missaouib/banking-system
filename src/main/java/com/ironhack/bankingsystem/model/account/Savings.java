package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.IncorrectInterestRate;
import com.ironhack.bankingsystem.exceptions.IncorrectMinimumBalance;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

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

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, Status.ACTIVE);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, Status.ACTIVE);
        this.secretKey = secretKey;
        setMinimumBalance(new BigDecimal(1000));
        setInterestRate(new BigDecimal(0.0025));
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
        if (minimumBalance.compareTo(new BigDecimal(100)) < 0){
            throw new IncorrectMinimumBalance();
        }
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal(0.5))>0){
            throw new IncorrectInterestRate();
        }
        this.interestRate = interestRate;
    }
}
