package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    private CreditCard account;
    private CreditCard accountNoInterest;

    @BeforeEach
    void beforeEach() {
        AccountHolder primaryOwner = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        account = new CreditCard(
                new Money(new BigDecimal(150)),
                primaryOwner, null
        );
        accountNoInterest = new CreditCard(
                new Money(new BigDecimal(600)),
                primaryOwner, null
        );
        account.setPaidInterestRate(LocalDate.now().minusDays(35));
    }

    @Test
    void applyInterestRate_applyInterestAndModifyDatePaidInterest() {
        account.applyInterestRate();
        assertEquals(new BigDecimal(153).setScale(2), account.getBalance().getAmount());
        assertEquals(LocalDate.now(), account.getPaidInterestRate());
    }

    @Test
    void applyInterestRate_noApplyInterest() {
        accountNoInterest.applyInterestRate();
        assertEquals(new BigDecimal(600).setScale(2), accountNoInterest.getBalance().getAmount());
    }

}