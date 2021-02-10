package com.ironhack.bankingsystem.model.account;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.AccountNoOwnerByName;
import com.ironhack.bankingsystem.exceptions.FrozenAccount;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
     void beforeEach() {
        AccountHolder primaryOwner = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
         account = new Checking(
                new Money(new BigDecimal(2000)),
                primaryOwner,null, "algo"
        );
    }

    @Test
    void toFrozen() {
        account.toFrozen();
        assertEquals(Status.FROZEN, account.getStatus());
    }

    @Test
    void checkFrozen_ThrowException() {
        account.toFrozen();
        assertThrows(FrozenAccount.class,()-> {
            account.checkFrozen();
        });
    }

    @Test
    void isOwnedBy_ThrowException() {
        assertThrows(AccountNoOwnerByName.class,()-> {
            account.isOwnedBy("sergio");
        });
    }
    @Test
    void isOwnedBy_Owner() {
        account.isOwnedBy("nerea");
    }

}