package com.ironhack.bankingsystem.repository.user;

import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.CreditCard;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.User;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;

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
        accountHolderRepository.saveAll(List.of(accountHolder,accountHolder2));

    }

    @Test
    void findByUsername_correct() {
        Optional<User> user = userRepository.findByUsername(accountHolder.getUsername());
        Optional<User> user2 = userRepository.findByUsername(accountHolder2.getUsername());

        assertEquals("nerea", user.get().getUsername());
        assertEquals("123456", user.get().getPassword());
        assertEquals("sergio", user2.get().getUsername());
        assertEquals("456789", user2.get().getPassword());
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
    }





}