package com.ironhack.bankingsystem.service.impl.accounts;

import com.ironhack.bankingsystem.dto.accounts.TransactionDTO;
import com.ironhack.bankingsystem.enums.Status;
import com.ironhack.bankingsystem.exceptions.FrozenAccount;
import com.ironhack.bankingsystem.exceptions.InsufficientFunds;
import com.ironhack.bankingsystem.exceptions.NoPresentAccount;
import com.ironhack.bankingsystem.model.account.Account;
import com.ironhack.bankingsystem.model.account.Checking;
import com.ironhack.bankingsystem.model.account.Savings;
import com.ironhack.bankingsystem.model.transaction.Transaction;
import com.ironhack.bankingsystem.model.user.User;
import com.ironhack.bankingsystem.repository.accounts.*;
import com.ironhack.bankingsystem.repository.transaction.TransactionRepository;
import com.ironhack.bankingsystem.service.interfaces.accounts.ITransactionService;
import com.ironhack.bankingsystem.utils.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * service to check the balance
     */
    public void transferMoney(TransactionDTO transactiondto, UserDetails user) {

        Account origin = accountRepository.findById(transactiondto.getOriginAccountId()).orElseThrow(NoPresentAccount::new);
        Account receiver = accountRepository.findById(transactiondto.getReceiverAccountId()).orElseThrow(NoPresentAccount::new);
        origin.isOwnedBy(user.getUsername());


        receiver.isOwnedBy(transactiondto.getNameOwner());
        fraudDetectionInDay(origin);
        fraudDetectionBySeconds(origin);
        origin.checkFrozen();

        if (origin.getBalance().getAmount().compareTo(transactiondto.getAmount()) < 0) {
            throw new InsufficientFunds();
        }

        origin.getBalance().decreaseAmount(transactiondto.getAmount());
        receiver.getBalance().increaseAmount(transactiondto.getAmount());

        if (origin instanceof Checking) {
            ((Checking) origin).chargePenaltyFee();
        } else if (origin instanceof Savings) {
            ((Savings) origin).chargePenaltyFee();
        }

        Transaction transaction = new Transaction(LocalDateTime.now(), new Money(transactiondto.getAmount()), origin, receiver);

        transactionRepository.save(transaction);
        accountRepository.save(origin);
        accountRepository.save(receiver);
    }

    public void fraudDetectionInDay(Account account) {

        Object[][] maxOperations = transactionRepository.getMaximumTransactionInDay();
        if (maxOperations != null && maxOperations.length > 0) {
            Object[][] userOperations = transactionRepository.getTransactionByOriginAccount(account.getId());
            if (userOperations != null && userOperations.length > 0) {
                if (((BigInteger) userOperations[0][0]).intValue() + 1 >= ((BigInteger) maxOperations[0][0]).intValue() * 1.5) {
                    freezeAccount(account);
                }
            }
        }
    }

    public void fraudDetectionBySeconds(Account account) {

        List<Transaction> transactions = account.getOriginTransactions();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        if (lastTransaction.getCreationDate().until(LocalDateTime.now(), ChronoUnit.MILLIS) < 1000) {
            freezeAccount(account);
        }

    }

    /**
     * change account status by frozen, save account, return exception
     */
    private void freezeAccount(Account account) {
        account.toFrozen();
        accountRepository.save(account);
        throw new FrozenAccount();
    }

}
