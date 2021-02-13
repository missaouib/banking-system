package com.ironhack.bankingsystem.service.impl.user;

import com.ironhack.bankingsystem.dto.user.ThirdPartyTransactionDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.*;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.model.user.ThirdParty;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.repository.user.ThirdPartyRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ThirdPartyServiceTest {

    @MockBean
    private ThirdPartyRepository thirdPartyRepository;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private ThirdPartyService thirdPartyService;

    private AccountHolder accountHolder;
    private Account account;
    private CustomUserDetails customUserDetails;
    private ThirdParty thirdParty;
    private ThirdPartyTransactionDTO thirdPartyTransactionDto;

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
        account.setId(1);
        customUserDetails = new CustomUserDetails(accountHolder);
        thirdParty = new ThirdParty("nerea", "123456", "123456");
        thirdParty.setId(1);
        thirdPartyTransactionDto = new ThirdPartyTransactionDTO(1, "123456", 1, new BigDecimal(100), "algp");
    }

    @Test
    void sendMoney_notAccount_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoPresentAccount.class, () -> thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetails));
    }

    @Test
    void sendMoney_notThirdParty_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoPresentThirdParty.class, () -> thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetails));

    }

    @Test
    void sendMoney_notEqualsThirdPartyUserName_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));
        accountHolder.setUsername("sergio");
        CustomUserDetails customUserDetailsResponse = new CustomUserDetails(accountHolder);
        assertThrows(UnauthorizedAccess.class, () -> thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetailsResponse));
    }

    @Test
    void sendMoney_noEqualsThirdPartyHashedKey_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));
        thirdParty.setHashedKey("25");
        assertThrows(HashedKeyIncorrect.class, () -> thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetails));
    }

    @Test
    void sendMoney_tThirdPartyStatusFreeze_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));
        account.setStatus(Status.FROZEN);
        assertThrows(FrozenAccount.class, () -> thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetails));
    }

    @Test
    void sendMoney_Correct() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));

        thirdPartyService.sendMoney(thirdPartyTransactionDto, customUserDetails);
        account.setBalance(new Money(new BigDecimal(2100)));

        verify(accountRepository).save(account);
        assertEquals(account.getBalance().getAmount(), new BigDecimal(2100).setScale(2));
        assertEquals(account.getBalance().getCurrency(), Currency.getInstance("USD"));
    }

    @Test
    void receiveMoney_InsufficientFunds_Exception() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));
        account.setBalance(new Money(new BigDecimal(50)));

        assertThrows(InsufficientFunds.class, () -> thirdPartyService.receiveMoney(thirdPartyTransactionDto, customUserDetails));

    }

    @Test
    void receiveMoney_Correct() {
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(thirdPartyRepository.findById(1)).thenReturn(Optional.of(thirdParty));

        thirdPartyService.receiveMoney(thirdPartyTransactionDto, customUserDetails);
        account.setBalance(new Money(new BigDecimal(1900)));

        verify(accountRepository).save(account);
    }


}