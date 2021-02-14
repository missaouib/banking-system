package com.ironhack.bankingsystem.repository.accounts;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    private Account accountChecking;
    private Account accountCreditCard;
    private AccountHolder accountHolder;
    private AccountHolder accountHolder2;

    @BeforeEach
    void setUp() {

        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountHolder2 = new AccountHolder(
                "sergio",
                "456789",
                LocalDate.of(2000, 05, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountChecking = new Checking(new Money(new BigDecimal(2000)), accountHolder, null, "algo");
        accountCreditCard = new CreditCard(new Money(new BigDecimal(2000)), accountHolder, accountHolder2);
        accountCreditCard.setStatus(Status.FROZEN);
        accountHolderRepository.saveAll(List.of(accountHolder,accountHolder2));
        accountRepository.saveAll(List.of(accountChecking,accountCreditCard));
    }


    @Test
    void findByPrimaryOwnerOrSecondaryOwner() {
        List<Account> accounts = accountRepository.findByPrimaryOwnerOrSecondaryOwner(accountHolder2,accountHolder2);

        assertEquals(1, accounts.size());
        assertEquals(accounts.get(0).getStatus(), Status.FROZEN);
    }

    @Test
    void findByStatus() {
        List<Account> accounts = accountRepository.findByStatus(Status.ACTIVE);

        assertEquals(1, accounts.size());
        assertEquals(accounts.get(0).getStatus(), Status.ACTIVE);

    }
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();

    }
}