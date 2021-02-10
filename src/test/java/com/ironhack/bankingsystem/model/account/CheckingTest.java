package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    private Checking account;
    private Checking accountNoPenalty;

    @BeforeEach
    void beforeEach() {
        AccountHolder primaryOwner = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        account = new Checking(
                new Money(new BigDecimal(150)),
                primaryOwner,null, "algo"
        );
        accountNoPenalty= new Checking(
                new Money(new BigDecimal(600)),
                primaryOwner,null, "algo"
        );
    }

    @Test
    void chargePenaltyFee_applyPenalty() {
        account.chargePenaltyFee();
        assertEquals(new BigDecimal(110).setScale(2), account.getBalance().getAmount());
    }

    @Test
    void chargePenaltyFee_notApplyPenalty() {
        accountNoPenalty.chargePenaltyFee();
        assertEquals(new BigDecimal(600).setScale(2), accountNoPenalty.getBalance().getAmount());
    }

}