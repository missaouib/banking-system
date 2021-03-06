package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class CreditCardDTO {

    @NotNull(message = "the balance is required")
    private Money balance;
    @NotNull(message = "the primaryOwnerId is required")
    private Integer primaryOwnerId;
    private Integer secondaryOwnerId;
    @Min(value = 100,message = "Credit Limit should be greater than 100")
    @Max(value = 100000, message = "Credit Limit should be less than 100000")
    private BigDecimal creditLimit;
    @DecimalMin(value = "0.1", message = "Interest Rate should be greater than 0.1")
    @DecimalMax(value = "0.2", message = "Interest Rate should be lees than 0.2")
    private BigDecimal interestRate;


    public CreditCardDTO(@NotNull(message = "the balance is required") Money balance, @NotNull(message = "the primaryOwnerId is required") Integer primaryOwnerId, Integer secondaryOwnerId, @Min(value = 100, message = "Credit Limit should be greater than 100") @Max(value = 100000, message = "Credit Limit should be less than 100000") BigDecimal creditLimit, @DecimalMin(value = "0.1", message = "Interest Rate should be greater than 0.1") @DecimalMax(value = "0.2", message = "Interest Rate should be lees than 0.2") BigDecimal interestRate) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Money getBalance() {
        return balance;
    }

    public Integer getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public Integer getSecondaryOwnerId() {
        return secondaryOwnerId;
    }


    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardDTO that = (CreditCardDTO) o;
        return Objects.equals(balance, that.balance) && Objects.equals(primaryOwnerId, that.primaryOwnerId) && Objects.equals(secondaryOwnerId, that.secondaryOwnerId) && Objects.equals(creditLimit, that.creditLimit) && Objects.equals(interestRate, that.interestRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, primaryOwnerId, secondaryOwnerId, creditLimit, interestRate);
    }
}
