package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.exceptions.FrozenAccount;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.model.account.*;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.model.user.AccountHolder;
import com.ironhack.bankingsystem.model.user.Address;
import com.ironhack.bankingsystem.repository.accounts.AccountRepository;
import com.ironhack.bankingsystem.repository.transaction.TransactionRepository;
import com.ironhack.bankingsystem.repository.user.AccountHolderRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.utils.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private AccountHolderRepository accountHolderRepository;
    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    private Account accountOrigin;
    private Account accountReceive;
    private Account accountOriginSaving;
    private AccountHolder accountHolder;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {

        accountHolder = new AccountHolder(
                "nerea",
                "123456",
                LocalDate.of(1993, 11, 30),
                new Address("lele", "28053", "Madrid", "españa"), null);
        accountOrigin = new Checking(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        accountOrigin.setId(1);
        accountOriginSaving = new Savings(
                new Money(new BigDecimal(2000)),
                accountHolder, null, "algo");
        accountOriginSaving.setId(3);
        accountReceive = new StudentChecking(new Money(new BigDecimal(1500)), accountHolder, null, "hola");
        accountReceive.setId(2);
        customUserDetails = new CustomUserDetails(accountHolder);

    }
    @Test
    void transferMoney_anyAccount_transaction() {
        TransactionDTO transactiondto = new TransactionDTO(1,2,new BigDecimal(500),"nerea");
        Object[][] maximumOperations = new Object[1][1];
        maximumOperations[0][0]= new BigInteger("2");
        Object[][] userOperations = new Object[1][1];
        userOperations[0][0]= new BigInteger("1");
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountOrigin));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));
        when(transactionRepository.getMaximumTransactionInDay()).thenReturn(maximumOperations);
        when(transactionRepository.getTransactionByOriginAccount(1)).thenReturn(userOperations);
        accountOrigin.getBalance().decreaseAmount(new BigDecimal(500));
        accountReceive.getBalance().increaseAmount(new BigDecimal(500));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);
        when(accountRepository.save(accountOrigin)).thenReturn(accountOrigin);
        when(accountRepository.save(accountReceive)).thenReturn(accountReceive);

        transactionService.transferMoney(transactiondto,customUserDetails);

        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(accountOrigin);
        verify(accountRepository).save(accountReceive);
    }
    @Test
    void transferMoney_SavingAccount_transaction() {
        TransactionDTO transactiondto = new TransactionDTO(3,2,new BigDecimal(500),"nerea");
        Object[][] maximumOperations = new Object[1][1];
        maximumOperations[0][0]= new BigInteger("2");
        Object[][] userOperations = new Object[1][1];
        userOperations[0][0]= new BigInteger("1");
        when(accountRepository.findById(3)).thenReturn(Optional.of(accountOriginSaving));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));
        when(transactionRepository.getMaximumTransactionInDay()).thenReturn(maximumOperations);
        when(transactionRepository.getTransactionByOriginAccount(1)).thenReturn(userOperations);
        accountOriginSaving.getBalance().decreaseAmount(new BigDecimal(500));
        accountReceive.getBalance().increaseAmount(new BigDecimal(500));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);
        when(accountRepository.save(accountOriginSaving)).thenReturn(accountOriginSaving);
        when(accountRepository.save(accountReceive)).thenReturn(accountReceive);

        transactionService.transferMoney(transactiondto,customUserDetails);

        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(accountOriginSaving);
        verify(accountRepository).save(accountReceive);
    }

    @Test
    void transferMoney_CheckingAccount_transaction() {
        TransactionDTO transactiondto = new TransactionDTO(1,2,new BigDecimal(500),"nerea");
        Object[][] maximumOperations = new Object[1][1];
        maximumOperations[0][0]= new BigInteger("2");
        Object[][] userOperations = new Object[1][1];
        userOperations[0][0]= new BigInteger("1");
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountOrigin));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));
        when(transactionRepository.getMaximumTransactionInDay()).thenReturn(maximumOperations);
        when(transactionRepository.getTransactionByOriginAccount(1)).thenReturn(userOperations);
        accountOrigin.getBalance().decreaseAmount(new BigDecimal(500));
        accountReceive.getBalance().increaseAmount(new BigDecimal(500));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(null);
        when(accountRepository.save(accountOrigin)).thenReturn(accountOrigin);
        when(accountRepository.save(accountReceive)).thenReturn(accountReceive);

        transactionService.transferMoney(transactiondto,customUserDetails);

        verify(transactionRepository).save(any(Transaction.class));
        verify(accountRepository).save(accountOrigin);
        verify(accountRepository).save(accountReceive);
    }


    @Test
    void transferMoney_transaction_exceptionInsufficientFunds() {
        TransactionDTO transactiondto = new TransactionDTO(1,2,new BigDecimal(5000),"nerea");
        Object[][] maximumOperations = new Object[1][1];
        maximumOperations[0][0]= new BigInteger("2");
        Object[][] userOperations = new Object[1][1];
        userOperations[0][0]= new BigInteger("1");
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountOrigin));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));
        when(transactionRepository.getMaximumTransactionInDay()).thenReturn(maximumOperations);
        when(transactionRepository.getTransactionByOriginAccount(1)).thenReturn(userOperations);

        assertThrows(InsufficientFunds.class,()-> transactionService.transferMoney(transactiondto,customUserDetails));

    }

    @Test
    void transferMoney_fraudDetectionInDay_exceptionFrozenAccount() {
        TransactionDTO transactiondto = new TransactionDTO(1,2,new BigDecimal(500),"nerea");
        Object[][] maximumOperations = new Object[1][1];
        maximumOperations[0][0]= new BigInteger("2");
        Object[][] userOperations = new Object[1][1];
        userOperations[0][0]= new BigInteger("3");
       when(accountRepository.findById(1)).thenReturn(Optional.of(accountOrigin));
       when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));
       when(transactionRepository.getMaximumTransactionInDay()).thenReturn(maximumOperations);
       when(transactionRepository.getTransactionByOriginAccount(1)).thenReturn(userOperations);

       assertThrows(FrozenAccount.class,()-> transactionService.transferMoney(transactiondto,customUserDetails));
    }


    @Test
    void transferMoney_fraudDetectionBySeconds() {
        accountOrigin.setOriginTransactions(List.of(new Transaction(LocalDateTime.now(),new Money(new BigDecimal(500)),accountOrigin,accountReceive)));
        TransactionDTO transactiondto = new TransactionDTO(1,2,new BigDecimal(500),"nerea");
        when(accountRepository.findById(1)).thenReturn(Optional.of(accountOrigin));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountReceive));

        assertThrows(FrozenAccount.class,()-> transactionService.transferMoney(transactiondto,customUserDetails));
    }
}