package com.ironhack.bankingsystem.dto.accounts;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class AmountDTO {

    @NotNull
    private BigDecimal amount;

    public AmountDTO() {
    }

    public AmountDTO(@NotNull BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountDTO amountDTO = (AmountDTO) o;
        return Objects.equals(amount, amountDTO.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
