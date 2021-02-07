package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class SavingDTO {

    @NotNull(message = "the balance is required")
    private Money balance;
    @NotNull(message = "the primaryOwnerId is required")
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @NotNull(message = "the penaltyFee is required")
    private BigDecimal penaltyFee;
    @NotEmpty(message = "the secretKey is required")
    private String secretKey;
    @Min(value = 100,message = "Minimum balance should be greater than 100")
    private BigDecimal minimumBalance;
    @DecimalMax(value = "0.50", message = "The interest rate should be less than 0.5")
    private BigDecimal interestRate;

    public SavingDTO(@NotEmpty(message = "the balance is required") Money balance, @NotEmpty(message = "the primaryOwnerId is required") Integer primaryOwnerId, Integer secondaryOwnerId, @NotEmpty(message = "the penaltyFee is required") BigDecimal penaltyFee, @NotEmpty(message = "the secretKey is required") String secretKey, @NotEmpty(message = "the minimumBalance is required") BigDecimal minimumBalance, @NotEmpty(message = "the interestRate is required") BigDecimal interestRate) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.penaltyFee = penaltyFee;
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
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

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
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
