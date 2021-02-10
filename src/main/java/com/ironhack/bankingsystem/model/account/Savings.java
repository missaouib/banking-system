package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings  extends Account {

    private String secretKey;
    private BigDecimal minimumBalance;
    @Column(precision = 11,scale = 4)
    private BigDecimal interestRate;
    private LocalDate paidInterestRate;


    public Savings() {
    }

    public Savings(String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner,  Status.ACTIVE);
        this.secretKey = secretKey;
        setPaidInterestRate(getCreationDate());
        setMinimumBalance(new BigDecimal(1000));
        setInterestRate(new BigDecimal(0.0025).setScale(4, RoundingMode.HALF_EVEN));

    }

    public void applyInterestRate(){
        Period period = Period.between(paidInterestRate, LocalDate.now());
        if (period.getYears() >= 1){
            getBalance().increaseAmount(getBalance().getAmount().multiply(getInterestRate()).setScale(2,RoundingMode.HALF_EVEN));
            setPaidInterestRate(LocalDate.now());
        }
    }


    public void chargePenaltyFee(){
        if(minimumBalance.compareTo(getBalance().getAmount()) > 0 ){
            getBalance().decreaseAmount(getPenaltyFee());
        }
    }


    public LocalDate getPaidInterestRate() {
        return paidInterestRate;
    }

    public void setPaidInterestRate(LocalDate paidInterestRate) {
        this.paidInterestRate = paidInterestRate;
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
