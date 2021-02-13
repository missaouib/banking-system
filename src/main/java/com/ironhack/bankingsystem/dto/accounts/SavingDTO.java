package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class SavingDTO {

    @NotNull(message = "the balance is required")
    private Money balance;
    @NotNull(message = "the primaryOwnerId is required")
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @NotEmpty(message = "the secretKey is required")
    private String secretKey;
    @Min(value = 100,message = "Minimum balance should be greater than 100")
    private BigDecimal minimumBalance;
    @DecimalMax(value = "0.50", message = "The interest rate should be less than 0.5")
    private BigDecimal interestRate;

    public SavingDTO(@NotEmpty(message = "the balance is required") Money balance, @NotEmpty(message = "the primaryOwnerId is required") Integer primaryOwnerId, Integer secondaryOwnerId, @NotEmpty(message = "the secretKey is required") String secretKey, @NotEmpty(message = "the minimumBalance is required") BigDecimal minimumBalance, @NotEmpty(message = "the interestRate is required") BigDecimal interestRate) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
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

    public Integer getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavingDTO savingDTO = (SavingDTO) o;
        return Objects.equals(balance, savingDTO.balance) && Objects.equals(primaryOwnerId, savingDTO.primaryOwnerId) && Objects.equals(secondaryOwnerId, savingDTO.secondaryOwnerId) && Objects.equals(secretKey, savingDTO.secretKey) && Objects.equals(minimumBalance, savingDTO.minimumBalance) && Objects.equals(interestRate, savingDTO.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, primaryOwnerId, secondaryOwnerId, secretKey, minimumBalance, interestRate);
    }
}
