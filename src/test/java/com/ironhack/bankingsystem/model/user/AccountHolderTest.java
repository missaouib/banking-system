package com.ironhack.bankingsystem.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountHolderTest {

    private AccountHolder accountHolderOlder;
    private AccountHolder accountHolderYoung;
    @BeforeEach
    void beforeEach() {

        accountHolderOlder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountHolderYoung = new AccountHolder(
                "sergio",
                "123456",
                LocalDate.of(2000, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
    }

    @Test
    void isOlderThan24_isTrue() {
        assertTrue(accountHolderOlder.isOlderThan24());
    }
    @Test
    void isOlderThan24_isFalse() {

        assertFalse(accountHolderYoung.isOlderThan24());
    }


}