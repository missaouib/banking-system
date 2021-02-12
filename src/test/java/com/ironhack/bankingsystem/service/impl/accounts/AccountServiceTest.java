package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.BalanceDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.exceptions.NoPresentAccountHolder;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountService accountService;

    private Account account;
    private Account accountStudent;
    private Account accountCreditCard;
    private Account accountSaving;
    private AccountHolder accountHolder;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        account = new Checking(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        accountStudent = new StudentChecking(new Money(new BigDecimal(1500)), accountHolder, null, "hola");
        accountCreditCard = new CreditCard(new Money(new BigDecimal(2000)), accountHolder, null);
        accountSaving = new Savings(new Money(new BigDecimal(1000)), accountHolder, null, "hola");
        customUserDetails = new CustomUserDetails(accountHolder);
    }

    @Test
    void findAll() {
        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<Account> accounts = accountService.findAll();

        assertEquals(List.of(account), accounts);
    }

    @Test
    void findByStatus_active() {
        when(accountRepository.findByStatus(Status.ACTIVE)).thenReturn(List.of(account));

        List<Account> accounts = accountService.findByStatus(Status.ACTIVE);

        assertEquals(List.of(account), accounts);
    }

    @Test
    void findByStatus_frozen_emptyList() {
        when(accountRepository.findByStatus(Status.FROZEN)).thenReturn(List.of());

        List<Account> accounts = accountService.findByStatus(Status.FROZEN);

        assertEquals(List.of(), accounts);
    }

    @Test
    void viewAccountsById_NoAccountHolder_Exception() {
        when(accountHolderRepository.findById(accountHolder.getId())).thenReturn(Optional.empty());

        assertThrows(NoPresentAccountHolder.class, () -> accountService.viewAccountsById(accountHolder.getId()));
    }

    @Test
    void viewAccountsById_AccountHolder_ListAccount() {
        when(accountHolderRepository.findById(accountHolder.getId())).thenReturn(Optional.of(accountHolder));
        when(accountRepository.findByPrimaryOwnerOrSecondaryOwner(accountHolder, accountHolder)).thenReturn(List.of(account));

        List<Account> accounts = accountService.viewAccountsById(accountHolder.getId());

        assertEquals(List.of(account), accounts);

    }

    @Test
    void checkBalance_noAccount_Exception() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.empty());

        assertThrows(NoPresentAccount.class, () -> accountService.checkBalance(account.getId(), customUserDetails));
    }

    @Test
    void checkBalance_BalanceChecking() {
        BalanceDTO expectedBalanceDto = new BalanceDTO(new Money(new BigDecimal(2000)));
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        BalanceDTO responseBalanceDto = accountService.checkBalance(account.getId(), customUserDetails);

        assertEquals(expectedBalanceDto, responseBalanceDto);
    }

    @Test
    void checkBalance_BalanceStudentChecking() {
        BalanceDTO expectedBalanceDto = new BalanceDTO(new Money(new BigDecimal(1500)));
        when(accountRepository.findById(accountStudent.getId())).thenReturn(Optional.of(accountStudent));

        BalanceDTO responseBalanceDto = accountService.checkBalance(accountStudent.getId(), customUserDetails);

        assertEquals(expectedBalanceDto, responseBalanceDto);
    }

    @Test
    void checkBalance_BalanceSaving() {
        BalanceDTO expectedBalanceDto = new BalanceDTO(new Money(new BigDecimal(1000)));
        when(accountRepository.findById(accountSaving.getId())).thenReturn(Optional.of(accountSaving));
        when(accountRepository.save(accountSaving)).thenReturn(accountSaving);

        BalanceDTO responseBalanceDto = accountService.checkBalance(accountSaving.getId(),customUserDetails);

        assertEquals(expectedBalanceDto,responseBalanceDto);

    }

    @Test
    void checkBalance_BalanceCreditCard() {
        BalanceDTO expectedBalanceDto = new BalanceDTO(new Money(new BigDecimal(2000)));
        when(accountRepository.findById(accountCreditCard.getId())).thenReturn(Optional.of(accountCreditCard));
        when(accountRepository.save(accountCreditCard)).thenReturn(accountCreditCard);

        BalanceDTO responseBalanceDto = accountService.checkBalance(accountCreditCard.getId(),customUserDetails);

        assertEquals(expectedBalanceDto,responseBalanceDto);

    }
}