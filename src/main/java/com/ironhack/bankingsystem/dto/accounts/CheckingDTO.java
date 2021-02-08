package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CheckingDTO {

    @NotNull(message = "the balance is required")
    private Money balance;
    @NotNull(message = "the primaryOwnerId is required")
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @NotEmpty(message = "the secretKey is required")
    private String secretKey;
    @Min(value = 0, message = "should be greater than 0")
    private BigDecimal minimumBalance;
    @Min(value = 0, message = "should be greater than 0")
    private BigDecimal monthlyMaintenanceFee;


    public CheckingDTO(@NotNull(message = "the balance is required") Money balance, @NotNull(message = "the primaryOwnerId is required") Integer primaryOwnerId, Integer secondaryOwnerId, @NotEmpty(message = "the secretKey is required") String secretKey, @Min(value = 0, message = "should be greater than 0") BigDecimal minimumBalance, @Min(value = 0, message = "should be greater than 0") BigDecimal monthlyMaintenanceFee) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Integer getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Integer primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Integer getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Integer secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
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
}
