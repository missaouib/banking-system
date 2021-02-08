package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.NotNull;

public class BalanceDTO {

    @NotNull(message = "the balance is required")
    private Money balance;

    public BalanceDTO(@NotNull(message = "the balance is required") Money balance) {
        this.balance = balance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
