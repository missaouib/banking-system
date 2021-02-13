package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Integer getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckingDTO that = (CheckingDTO) o;
        return Objects.equals(balance, that.balance) && Objects.equals(primaryOwnerId, that.primaryOwnerId) && Objects.equals(secondaryOwnerId, that.secondaryOwnerId) && Objects.equals(secretKey, that.secretKey) && Objects.equals(minimumBalance, that.minimumBalance) && Objects.equals(monthlyMaintenanceFee, that.monthlyMaintenanceFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, primaryOwnerId, secondaryOwnerId, secretKey, minimumBalance, monthlyMaintenanceFee);
    }
}
