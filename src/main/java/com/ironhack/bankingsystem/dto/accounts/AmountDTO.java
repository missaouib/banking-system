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

}
