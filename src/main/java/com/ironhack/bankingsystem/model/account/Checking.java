package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.utils.Money;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    private String secretKey;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;

    public Checking() {
    }

    public Checking(String secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, Status.ACTIVE);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, Status.ACTIVE);
        this.secretKey = secretKey;
        setMinimumBalance(new BigDecimal(250));
        setMonthlyMaintenanceFee(new BigDecimal(12));
    }


    public void chargePenaltyFee() {
        if (minimumBalance.compareTo(getBalance().getAmount()) > 0) {
            getBalance().decreaseAmount(getPenaltyFee());
        }
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

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checking checking = (Checking) o;
        return secretKey.equals(checking.secretKey) && minimumBalance.equals(checking.minimumBalance) && monthlyMaintenanceFee.equals(checking.monthlyMaintenanceFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), secretKey, minimumBalance, monthlyMaintenanceFee);
    }
}
