package com.ironhack.bankingsystem.repository.transaction;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionRepositoryTest {



    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private TransactionRepository transactionRepository;

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
        Account accountChecking  = new Checking(new Money(new BigDecimal(2000)), accountHolder, null, "algo");
        Account accountCreditCard = new CreditCard(new Money(new BigDecimal(2000)), accountHolder, accountHolder2);
        accountCreditCard.setStatus(Status.FROZEN);
        Transaction transaction =new Transaction(LocalDateTime.now(),new Money(new BigDecimal(100)),accountChecking,accountCreditCard);
        Transaction transaction2 = new Transaction(LocalDateTime.of(2021, 02, 13,12,35,00,00),new Money(new BigDecimal(200)),accountCreditCard,accountChecking);
        Transaction transaction3 = new Transaction(LocalDateTime.of(2021, 02, 13,12,35,00,00),new Money(new BigDecimal(100)),accountCreditCard,accountChecking);
        Transaction transaction4 = new Transaction(LocalDateTime.of(2021, 02, 13,12,35,00,00),new Money(new BigDecimal(200)),accountCreditCard,accountChecking);
        accountHolderRepository.saveAll(List.of(accountHolder,accountHolder2));
        accountRepository.saveAll(List.of(accountChecking,accountCreditCard));
        transactionRepository.saveAll(List.of(transaction,transaction2,transaction3,transaction4));
    }

    @Test
    void getMaximumTransactionInDay() {
       Object[][] objects = transactionRepository.getMaximumTransactionInDay();
       assertEquals(new BigInteger("3"), objects[0][0]);
    }

    @Test
    void getTransactionByOriginAccount() {
        Object[][] objects = transactionRepository.getTransactionByOriginAccount(1);
        assertEquals(new BigInteger("1"), objects[0][0]);

    }


    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }


}