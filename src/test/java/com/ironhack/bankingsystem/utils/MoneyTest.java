package com.ironhack.bankingsystem.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {


    @Test
    void money_increaseAmount() {
        Money money = new Money(new BigDecimal("100"), Currency.getInstance("EUR"));

        money.increaseAmount(new Money(new BigDecimal("50")));

        assertEquals(new BigDecimal("150").setScale(2), money.getAmount());
    }

    @Test
    void money_decreaseAmount() {
        Money money = new Money(new BigDecimal("100"));

        money.decreaseAmount(new Money(new BigDecimal("50")));

        assertEquals(new BigDecimal("50").setScale(2), money.getAmount());
    }

}