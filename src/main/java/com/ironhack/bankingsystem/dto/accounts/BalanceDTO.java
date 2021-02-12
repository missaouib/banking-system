package com.ironhack.bankingsystem.dto.accounts;

import com.ironhack.bankingsystem.utils.Money;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDTO that = (BalanceDTO) o;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
}
