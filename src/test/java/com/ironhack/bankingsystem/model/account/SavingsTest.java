package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SavingsTest {
    private Savings account;
    private Savings account2;

    @BeforeEach
    void beforeEach() {
        AccountHolder primaryOwner = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        account = new Savings(
                new Money(new BigDecimal(150)),
                primaryOwner, null,"123456"
        );
        account2 = new Savings(
                new Money(new BigDecimal(1000)),
                primaryOwner, null, "123456"
        );
        account.setPaidInterestRate(LocalDate.now().minusYears(1));
    }

    @Test
    void applyInterestRate_applyInterestAndModifyDatePaidInterest() {
        account.applyInterestRate();
        assertEquals(new BigDecimal(150.38).setScale(2, RoundingMode.HALF_EVEN), account.getBalance().getAmount());
        assertEquals(LocalDate.now(), account.getPaidInterestRate());
    }

    @Test
    void applyInterestRate_noApplyInterest() {
        account2.applyInterestRate();
        assertEquals(new BigDecimal(1000).setScale(2), account2.getBalance().getAmount());
    }
    @Test
    void chargePenaltyFee_applyPenalty() {
        account.chargePenaltyFee();
        assertEquals(new BigDecimal(110).setScale(2), account.getBalance().getAmount());
    }

    @Test
    void chargePenaltyFee_notApplyPenalty() {
        account2.chargePenaltyFee();
        assertEquals(new BigDecimal(1000).setScale(2), account2.getBalance().getAmount());
    }
}