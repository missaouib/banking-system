package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.accounts.CreditCardDTO;
import com.ironhack.bankingsystem.dto.accounts.SavingDTO;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.repository.user.ThirdPartyRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.accounts.AccountService;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminServiceTest {
    @MockBean
    private  AccountRepository accountRepository;
    @MockBean
    private  SavingsRepository savingsRepository;
    @MockBean
    private  CreditCardRepository creditCardRepository;
    @MockBean
    private  CheckingRepository checkingRepository;
    @MockBean
    private  AccountHolderRepository accountHolderRepository;
    @MockBean
    private  StudentCheckingRepository studentCheckingRepository;
    @MockBean
    private  ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private AdminService adminService;

    private Checking accountChecking;
    private StudentChecking accountStudent;
    private CreditCard accountCreditCard;
    private Savings accountSaving;
    private AccountHolder accountHolder;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountChecking = new Checking(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        accountStudent = new StudentChecking(new Money(new BigDecimal(1500)), accountHolder, null, "hola");
        accountCreditCard = new CreditCard(new Money(new BigDecimal(2000)),accountHolder,null);
        accountSaving = new Savings(new Money(new BigDecimal(500)), accountHolder, null, "hola");
        customUserDetails = new CustomUserDetails(accountHolder);
    }

    @Test
    void createSavingAccount_defaultValues() {
        SavingDTO savingDto = new SavingDTO(new Money(new BigDecimal(500)),1,null,"hola",null,null);
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(savingsRepository.save(accountSaving)).thenReturn(accountSaving);

        Savings savingsResponse = adminService.createSavingAccount(savingDto);

        assertEquals(accountSaving, savingsResponse);
    }

    @Test
    void createSavingAccount_withValuesNoDefault() {
        Savings savings = new Savings(new Money(new BigDecimal(500)),accountHolder,accountHolder,"hola");
        savings.setMinimumBalance(new BigDecimal(500));
        savings.setInterestRate(new BigDecimal(0.05));
        SavingDTO savingDto = new SavingDTO(new Money(new BigDecimal(500)),1,1,"hola",new BigDecimal(500),new BigDecimal(0.05));
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(savingsRepository.save(savings)).thenReturn(savings);

        Savings savingsResponse = adminService.createSavingAccount(savingDto);

        assertEquals(savings, savingsResponse);
    }
    @Test
    void createCreditAccount_defaultValues() {
        CreditCardDTO creditCardDto = new CreditCardDTO(new Money(new BigDecimal(2000)),1,null,null,null);
        when(accountHolderRepository.findById(1)).thenReturn(Optional.of(accountHolder));
        when(creditCardRepository.save(accountCreditCard)).thenReturn(accountCreditCard);

        CreditCard creditCardResponse = adminService.createCreditCardAccount(creditCardDto);

        assertEquals(accountCreditCard, creditCardResponse);
    }



}